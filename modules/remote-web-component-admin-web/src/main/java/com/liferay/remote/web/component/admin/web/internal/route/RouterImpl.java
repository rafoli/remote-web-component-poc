package com.liferay.remote.web.component.admin.web.internal.route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liferay.portal.kernel.portlet.Route;
import com.liferay.portal.kernel.portlet.Router;

public class RouterImpl  implements Router {

	public RouterImpl() {
		_routes = new ArrayList<>();
	}

	public RouterImpl(int size) {
		_routes = new ArrayList<>(size);
	}

	@Override
	public Route addRoute(String pattern) {
		Route route = new RouteImpl(pattern);

		_routes.add(route);

		return route;
	}

	@Override
	public List<Route> getRoutes() {
		return new ArrayList<>(_routes);
	}

	@Override
	public String parametersToUrl(Map<String, String> parameters) {
		for (Route route : _routes) {
			String url = route.parametersToUrl(new HashMap<>(parameters));

			if (url != null) {
				return url;
			}
		}

		return null;
	}

	@Override
	public boolean urlToParameters(String url, Map<String, String> parameters) {
		for (Route route : _routes) {
			if (route.urlToParameters(url, parameters)) {
				return true;
			}
		}

		return false;
	}

	private final List<Route> _routes;

}