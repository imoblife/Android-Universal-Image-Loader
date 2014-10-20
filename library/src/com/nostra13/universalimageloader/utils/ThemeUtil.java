package com.nostra13.universalimageloader.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ThemeUtil {
	static final String TAG = "ThemeUtil";
	static String defalutPackageName;

	public static Context createRemoteContext(Context context,
			String applicationPackageName) {
		Context remoteContext = null;
		if (context == null || applicationPackageName == null
				|| "".equals(applicationPackageName)) {
			return null;
		} else {
			try {
				remoteContext = context.createPackageContext(
						applicationPackageName, Context.CONTEXT_IGNORE_SECURITY
								| Context.CONTEXT_INCLUDE_CODE);
				defalutPackageName = applicationPackageName;
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
			return remoteContext;
		}
	}

	public static Resources getRemoteResources(Context remoteContext) {
		Resources resources = null;
		if (remoteContext != null) {
			resources = remoteContext.getResources();
		}
		return resources;
	}

	public static int getResourceIdByFileName(Context remoteContext,
			String fileName, String defType, String applicationPackageName) {
		if (remoteContext == null || fileName == null || "".equals(fileName)
				|| defType == null || "".equals(defType)
				|| applicationPackageName == null
				|| "".equals(applicationPackageName)) {
			return -1;
		} else {
			Resources resources = getRemoteResources(remoteContext);
			int resourceId = resources.getIdentifier(fileName, defType,
					applicationPackageName);
			return resourceId;
		}
	}

	public static int getResourceIdByFileName(Context remoteContext,
			String fileName, String defType) {
		if (remoteContext == null || fileName == null || "".equals(fileName)
				|| defType == null || "".equals(defType)) {
			return -1;
		} else {
			Resources resources = getRemoteResources(remoteContext);
			int resourceId = resources.getIdentifier(fileName, defType,
					defalutPackageName);
			return resourceId;
		}
	}

	public static Drawable getDrawableByFileName(Context remoteContext,
			String fileName) {
		if (remoteContext == null || fileName == null || "".equals(fileName)) {
			return null;
		} else {
			Drawable drawable = null;
			int resourceId = getResourceIdByFileName(remoteContext, fileName,
					"drawable", defalutPackageName);
			Resources r = getRemoteResources(remoteContext);
			drawable = r.getDrawable(resourceId);
			return drawable;
		}
	}

	public static Drawable getDrawableByFileName(Context context,
			String packageName, String fileName) {
		return getDrawableByFileName(createRemoteContext(context, packageName),
				fileName);
	}

	public static int getColorByColorName(Context remoteContext,
			String colorName, String applicationPackageName) {
		if (remoteContext == null || colorName == null || "".equals(colorName)
				|| applicationPackageName == null
				|| "".equals(applicationPackageName)) {
			return 0;
		} else {
			int resourceId = getResourceIdByFileName(remoteContext, colorName,
					"color", applicationPackageName);
			Resources r = getRemoteResources(remoteContext);
			int color = r.getColor(resourceId);
			return color;
		}
	}

	public static View getViewByFileName(Context remoteContext,
			String fileName, String applicationPackageName, ViewGroup root,
			boolean attachToRoot) {
		if (remoteContext == null || fileName == null || "".equals(fileName)
				|| applicationPackageName == null
				|| "".equals(applicationPackageName)) {
			return null;
		} else {
			LayoutInflater remoteInflater = LayoutInflater.from(remoteContext);
			int resourceId = getResourceIdByFileName(remoteContext, fileName,
					"layout", applicationPackageName);
			View view = remoteInflater.inflate(resourceId, root, attachToRoot);
			return view;
		}
	}

	public static View getViewByFileName(Context remoteContext,
			String fileName, ViewGroup root, boolean attachToRoot) {
		if (remoteContext == null || fileName == null || "".equals(fileName)) {
			return null;
		} else {
			LayoutInflater remoteInflater = LayoutInflater.from(remoteContext);
			int resourceId = getResourceIdByFileName(remoteContext, fileName,
					"layout", defalutPackageName);
			View view = remoteInflater.inflate(resourceId, root, attachToRoot);
			return view;
		}
	}

	public static View getViewByFileName(Context remoteContext,
			String fileName, String applicationPackageName, ViewGroup root) {
		if (remoteContext == null || fileName == null || "".equals(fileName)
				|| applicationPackageName == null
				|| "".equals(applicationPackageName)) {
			return null;
		} else {
			LayoutInflater remoteInflater = LayoutInflater.from(remoteContext);
			int resourceId = getResourceIdByFileName(remoteContext, fileName,
					"layout", applicationPackageName);
			View view = remoteInflater.inflate(resourceId, root);
			return view;
		}
	}

	public static View getViewByFileName(Context remoteContext,
			String fileName, ViewGroup root) {
		if (remoteContext == null || fileName == null || "".equals(fileName)) {
			return null;
		} else {
			LayoutInflater remoteInflater = LayoutInflater.from(remoteContext);
			int resourceId = getResourceIdByFileName(remoteContext, fileName,
					"layout", defalutPackageName);
			View view = remoteInflater.inflate(resourceId, root);
			return view;
		}
	}
}