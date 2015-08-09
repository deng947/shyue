package org.cb.zframe.dao;

/**
 * Query Object
 * 
 * @author pesome
 * @date Nov 18, 2008
 */
public class QueryObject {
	public static String EQUAL = "=";
	public static String LARGE_THAN = ">";
	public static String LARGE_EUQAL = ">=";
	public static String SMALL_THAN = "<";
	public static String SMALL_EUQAL = "<=";
	public static String LIKE = "like";
	public static String BETWEEN = "between";
	public static int INT = 1; // 数字�?
	public static int STR = 2; // String类型
	/**
	 * Date类型 2000 代表按年查询 2000-12 按月查询 2000-12-12 按天查询
	 */
	public static int DATE = 3;
	private String name;
	private String fomular;
	private int type;
	private String value;
	private String collectionName;

	/**
	 * 
	 * @param name
	 *            查询条件 例如：name（这里要用对象属性）
	 * @param fomular
	 *            查询公式 例如:>=
	 * @param type
	 *            查询类型 现在有int,String和Date.
	 * @param value
	 *            查询值
	 */
	public QueryObject(String name, String fomular, String value, int type) {
		super();
		this.fomular = fomular;
		this.name = name;
		this.type = type;
		this.value = value;
	}

	/**
	 * 针对ManyToMany关联的查询，例如User对应多个Role,要查询有Role
	 * name为"a"的用户，则name="name",collectionName="roles"
	 * 
	 * @param name
	 * @param fomular
	 * @param value
	 * @param type
	 * @param collectionName
	 */
	public QueryObject(String name, String fomular, String value, int type,
			String collectionName) {
		super();
		this.fomular = fomular;
		this.name = name;
		this.type = type;
		this.value = value;
		this.collectionName = collectionName;
	}

	public QueryObject() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFomular() {
		return fomular;
	}

	public void setFomular(String fomular) {
		this.fomular = fomular;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.name).append(" ").append(this.fomular).append(" ");
		if (this.type == QueryObject.STR) {
			sb.append("'").append(this.value).append("'");
		} else if (this.type == QueryObject.INT) {
			sb.append(this.value);
		} else if (this.type == QueryObject.DATE) {
			if (this.fomular.equals(QueryObject.BETWEEN)) {
				if (value.length() == 4) {
					sb.append("'").append(value).append("-01-01 00:00:00.0'")
							.append(" and '").append(value).append(
									"-12-31 23:59:59.9'");
				} else if (value.length() == 7) {
					sb.append("'").append(value).append("-01 00:00:00.0'")
							.append(" and '").append(value).append(
									"-31 23:59:59.9'");
				} else if (value.length() == 10) {
					sb.append("'").append(value).append(" 00:00:00.0'").append(
							" and '").append(value).append(" 23:59:59.9'");
				}
			} else {
				if (this.fomular.equals(QueryObject.LARGE_EUQAL)) {
					if (value.length() == 4) {
						sb.append("'").append(value).append(
								"-01-01 00:00:00.0'");
					} else if (value.length() == 7) {
						sb.append("'").append(value).append("-01 00:00:00.0'");
					} else if (value.length() == 10) {
						sb.append("'").append(value).append(" 00:00:00.0'");
					}
				} else {
					if (this.fomular.equals(QueryObject.SMALL_EUQAL)) {
						if (value.length() == 4) {
							sb.append("'").append(value).append(
									"-12-31 23:59:59.9'");
						} else if (value.length() == 7) {
							sb.append("'").append(value).append(
									"-31 23:59:59.9'");
						} else if (value.length() == 10) {
							sb.append("'").append(value).append(" 23:59:59.9'");
						}
					}
				}
			}
		}
		return sb.toString();
	}

}
