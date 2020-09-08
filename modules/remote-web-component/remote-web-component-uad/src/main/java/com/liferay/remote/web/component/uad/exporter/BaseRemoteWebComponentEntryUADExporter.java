/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.remote.web.component.uad.exporter;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.remote.web.component.model.RemoteWebComponentEntry;
import com.liferay.remote.web.component.service.RemoteWebComponentEntryLocalService;
import com.liferay.remote.web.component.uad.constants.RemoteWebComponentUADConstants;
import com.liferay.user.associated.data.exporter.DynamicQueryUADExporter;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the remote web component entry UAD exporter.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link RemoteWebComponentEntryUADExporter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BaseRemoteWebComponentEntryUADExporter
	extends DynamicQueryUADExporter<RemoteWebComponentEntry> {

	@Override
	public Class<RemoteWebComponentEntry> getTypeClass() {
		return RemoteWebComponentEntry.class;
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return remoteWebComponentEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return RemoteWebComponentUADConstants.
			USER_ID_FIELD_NAMES_REMOTE_WEB_COMPONENT_ENTRY;
	}

	@Override
	protected String toXmlString(
		RemoteWebComponentEntry remoteWebComponentEntry) {

		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.remote.web.component.model.RemoteWebComponentEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>remoteWebComponentEntryId</column-name><column-value><![CDATA[");
		sb.append(remoteWebComponentEntry.getRemoteWebComponentEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(remoteWebComponentEntry.getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(remoteWebComponentEntry.getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(remoteWebComponentEntry.getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>url</column-name><column-value><![CDATA[");
		sb.append(remoteWebComponentEntry.getUrl());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	@Reference
	protected RemoteWebComponentEntryLocalService
		remoteWebComponentEntryLocalService;

}