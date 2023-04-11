package com.nickdferrara.service

import com.nickdferrara.models.Product
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.replaceOne
import org.litote.kmongo.eq
import java.util.*

class ProductService(
    database: CoroutineDatabase
) {
    private val productCollection = database.getCollection<Product>()

    suspend fun create(product: Product): Product  {
        product._id = UUID.randomUUID().toString()
        productCollection.insertOne(product)
        return product
    }

    suspend fun findById(id: String): Product? {
        return productCollection
            .findOne(Product::_id eq id)
    }

    suspend fun updateById(id: String, request: Product): Boolean =
        findById(id)
            ?.let { product ->
                val updateResult = productCollection.replaceOne(
                    product.copy(
                        name = request.name,
                        description = request.description,
                        quantity = request.quantity,
                        price = request.price,
                        image = request.image
                    )
                )
                updateResult.modifiedCount == 1L
            } ?: false

}