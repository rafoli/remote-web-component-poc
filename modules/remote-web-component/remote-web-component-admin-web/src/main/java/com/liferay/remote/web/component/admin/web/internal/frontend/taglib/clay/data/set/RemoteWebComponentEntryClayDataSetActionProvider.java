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

package com.liferay.remote.web.component.admin.web.internal.frontend.taglib.clay.data.set;

import com.liferay.frontend.taglib.clay.data.set.ClayDataSetActionProvider;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.remote.web.component.admin.web.internal.constants.RemoteWebComponentAdminConstants;

import java.util.List;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Basto
 */
@Component(
	immediate = true,
	property = "clay.data.provider.key=" + RemoteWebComponentAdminConstants.REMOTE_WEB_COMPONENT_ENTRY_DATA_SET_DISPLAY,
	service = ClayDataSetActionProvider.class
)
public class RemoteWebComponentEntryClayDataSetActionProvider
	implements ClayDataSetActionProvider {

	@Override
	public List<DropdownItem> getDropdownItems(
			HttpServletRequest httpServletRequest, long groupId, Object model)
		throws PortalException {

		RemoteWebComponentClayDataSetEntry remoteWebComponentClayDataSetEntry =
			(RemoteWebComponentClayDataSetEntry)model;

		return DropdownItemListBuilder.add(
			dropdownItem -> _buildEditRemoteWebComponentEntryAction(
				dropdownItem, httpServletRequest,
				remoteWebComponentClayDataSetEntry)
		).add(
			dropdownItem -> _buildDeleteWebComponentAppEntryAction(
				dropdownItem, httpServletRequest,
				remoteWebComponentClayDataSetEntry)
		).build();
	}

	private void _buildDeleteWebComponentAppEntryAction(
		DropdownItem dropdownItem, HttpServletRequest httpServletRequest,
		RemoteWebComponentClayDataSetEntry remoteWebComponentClayDataSetEntry) {

		PortletURL deleteRemoteWebComponentEntryURL = _getActionURL(
			httpServletRequest);

		deleteRemoteWebComponentEntryURL.setParameter(
			ActionRequest.ACTION_NAME, "/delete_web_component_app_entry");
		deleteRemoteWebComponentEntryURL.setParameter(
			"remoteWebComponentEntryId",
			String.valueOf(
				remoteWebComponentClayDataSetEntry.
					getRemoteWebComponentEntryId()));

		dropdownItem.setHref(deleteRemoteWebComponentEntryURL.toString());

		dropdownItem.setIcon("times-circle");
		dropdownItem.setLabel(_getMessage(httpServletRequest, "delete"));
	}

	private void _buildEditRemoteWebComponentEntryAction(
		DropdownItem dropdownItem, HttpServletRequest httpServletRequest,
		RemoteWebComponentClayDataSetEntry remoteWebComponentClayDataSetEntry) {

		PortletURL editRemoteWebComponentEntryURL = _getRenderURL(
			httpServletRequest);

		editRemoteWebComponentEntryURL.setParameter(
			"mvcRenderCommandName", "/edit_remote_web_component_entry");
		editRemoteWebComponentEntryURL.setParameter(
			"remoteWebComponentEntryId",
			String.valueOf(
				remoteWebComponentClayDataSetEntry.
					getRemoteWebComponentEntryId()));

		String currentURL = ParamUtil.getString(
			httpServletRequest, "currentURL",
			_portal.getCurrentURL(httpServletRequest));

		editRemoteWebComponentEntryURL.setParameter("redirect", currentURL);

		dropdownItem.setHref(editRemoteWebComponentEntryURL);
		dropdownItem.setLabel(_getMessage(httpServletRequest, "edit"));
	}

	private PortletURL _getActionURL(HttpServletRequest httpServletRequest) {
		String portletId = _getPortletId(httpServletRequest);

		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(httpServletRequest);

		return requestBackedPortletURLFactory.createActionURL(portletId);
	}

	private String _getMessage(
		HttpServletRequest httpServletRequest, String key) {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", themeDisplay.getLocale(), getClass());

		return LanguageUtil.get(resourceBundle, key);
	}

	private String _getPortletId(HttpServletRequest httpServletRequest) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		return portletDisplay.getId();
	}

	private PortletURL _getRenderURL(HttpServletRequest httpServletRequest) {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(httpServletRequest);

		String portletId = _getPortletId(httpServletRequest);

		return requestBackedPortletURLFactory.createRenderURL(portletId);
	}

	@Reference
	private Portal _portal;

	@Reference
	private PortletLocalService _portletLocalService;

}