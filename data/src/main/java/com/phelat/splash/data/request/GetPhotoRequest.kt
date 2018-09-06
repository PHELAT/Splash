package com.phelat.splash.data.request

import com.phelat.splash.data.const.OrderBy

data class GetPhotoRequest(val page: Int,
                           val perPage: Int = 24,
                           val orderBy: String = OrderBy.LATEST)