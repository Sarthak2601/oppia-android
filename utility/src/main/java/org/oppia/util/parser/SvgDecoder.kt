package org.oppia.util.parser

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.oppia.util.crashlytics.CrashlyticsWrapper
import java.io.IOException
import java.io.InputStream

/** Decodes an SVG internal representation from an {@link InputStream}. */
class SvgDecoder : ResourceDecoder<InputStream?, SVG?> {
  private val crashlyticsWrapper = CrashlyticsWrapper()

  override fun handles(source: InputStream, options: Options): Boolean {
    return true
  }

  override fun decode(
    source: InputStream,
    width: Int,
    height: Int,
    options: Options
  ): Resource<SVG?>? {
    return try {
      SimpleResource(source.use { SVG.getFromInputStream(it) })
    } catch (ex: SVGParseException) {
      crashlyticsWrapper.logException(ex)
      throw IOException("Cannot load SVG from stream", ex)
    }
  }
}
