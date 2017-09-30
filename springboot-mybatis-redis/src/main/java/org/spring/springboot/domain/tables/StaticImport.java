package org.spring.springboot.domain.tables;

import com.welab.xdao.common.DaoContext;
import com.welab.xdao.context.table.Tables;

public class StaticImport extends DaoContext {

	public static final TAreas tAreas = Tables.get(TAreas.class);
	public static final TCity tCity = Tables.get(TCity.class);
	public static final TCitya tCitya = Tables.get(TCitya.class);
	public static final TUser tUser = Tables.get(TUser.class);

}

