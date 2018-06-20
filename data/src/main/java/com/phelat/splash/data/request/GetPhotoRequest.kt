package com.phelat.splash.data.request

import com.phelat.splash.data.const.OrderBy

/**
 * Created by MAHDi on 6/4/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

data class GetPhotoRequest(val page: Int,
                           val perPage: Int = 24,
                           val orderBy: String = OrderBy.LATEST)