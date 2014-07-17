package com.nostra13.universalimageloader.core.download;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.nostra13.universalimageloader.core.assist.ContentLengthInputStream;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

public class PackageImageDownloader extends BaseImageDownloader {

	private static final String PACKAGE_SCHEME = "package";
	private static final String PACKAGE_PREFIX = PACKAGE_SCHEME + "://";

	public PackageImageDownloader(Context context) {
		super(context);
	}

	public PackageImageDownloader(Context context, int connectTimeout,
			int readTimeout) {
		super(context, connectTimeout, readTimeout);
	}

	protected InputStream getStreamFromPackage(String imageUri, Object extra)
			throws IOException {
		if (imageUri.startsWith(PACKAGE_PREFIX)) {
			InputStream is = null;
			try {
				String pkgName = imageUri.substring(PACKAGE_PREFIX.length());
				ApplicationInfo ai = context.getPackageManager()
						.getApplicationInfo(pkgName, 1);
				BitmapDrawable d = (BitmapDrawable) ai.loadIcon(context
						.getPackageManager());
				Bitmap bitmap = d.getBitmap();

				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byte[] imageInByte = stream.toByteArray();
				ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);
				is = new ContentLengthInputStream(bis, imageInByte.length);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return is;
		}
		return super.getStreamFromOtherSource(imageUri, extra);
	}
}
