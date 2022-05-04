package dev.ky3he4ik.pract15.network

data class VkResponse(val response: List<VkUser>? = null)

data class VkUser(
    val id: Int = 0, val first_name: String = "", val last_name: String = "",
    val can_access_closed: Boolean = false, val is_closed: Boolean = false
)
