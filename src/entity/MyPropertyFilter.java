package entity;

import com.alibaba.fastjson.serializer.PropertyFilter;

/**
 * fastJSON防止序列化递归：PropertyFilter
 * PropertyFilter可以过滤不相关属性
 * @author 丰源
 *
 */
public class MyPropertyFilter implements PropertyFilter {

	public MyPropertyFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean apply(Object arg0, String name, Object arg2) {
		// TODO Auto-generated method stub
		//name：属性名
		//return false:过滤掉该属性
		if(name.equalsIgnoreCase("orders")){
			return false;
		}
		if(name.equalsIgnoreCase("self_pai_pic")){
			return false;
		}
		return true;
	}

}
