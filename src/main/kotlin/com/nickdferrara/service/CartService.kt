package com.nickdferrara.service

import com.nickdferrara.models.Cart
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.replaceOne
import org.litote.kmongo.eq
import java.util.*

class CartService(
    database: CoroutineDatabase
) {
    private val cartCollection = database.getCollection<Cart>()

    suspend fun create(cart: Cart): Cart  {
        cart._id = UUID.randomUUID().toString()
        cartCollection.insertOne(cart)
        return cart
    }

    suspend fun findById(id: String): Cart? {
        return cartCollection
            .findOne(Cart::_id eq id)
    }

    suspend fun updateById(id: String, request: Cart): Boolean =
        findById(id)
            ?.let { cart ->
                val updateResult = cartCollection.replaceOne(
                    cart.copy(
                        productList = request.productList
                    )
                )
                updateResult.modifiedCount == 1L
            } ?: false

    suspend fun deleteById(id: String): Boolean {
        val deleteResult = cartCollection.deleteOneById(ObjectId(id))
        return deleteResult.deletedCount == 1L
    }
}