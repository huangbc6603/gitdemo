package org.example.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Derek-huang
 */
public class PaAssembleUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Object> conditionFilter;

    private Map<String,Object> paramObject;

    private String NodeName;

    private Object KeyVaue;

    public Object getKeyVaue() {
        return KeyVaue;
    }

    public Object addKeyVaue(JSONObject... objs) {
        this.KeyVaue = toJSONWriteMapNullValue(objs);
        return JSON.toJSON(this);
    }

    public String getNodeName() {
        return NodeName;
    }

    public PaAssembleUtil addNodeName(String nodeName) {
        this.NodeName = nodeName;
        return this;
    }

    public List<Object> getConditionFilter() {
        return conditionFilter;
    }

    public void setConditionFilter(List<Object> conditionFilter) {
        this.conditionFilter = conditionFilter;
    }

    public Map<String, Object> getParamObject() {
        return paramObject;
    }

    public void setParamObject(Map<String, Object> paramObject) {
        this.paramObject = paramObject;
    }

    public PaAssembleUtil() {
        super();
    }

    public static PaAssembleUtil build(){
        return new PaAssembleUtil();
    }

    public Object toJSON(){
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 解决value为null  toJSON时被过滤导致ADE匹配出错
     * @param
     * @return List<Object>
     * */
    private Object toJSONWriteMapNullValue(JSONObject... objs){
        List<Object> list = Lists.newArrayList();
        for(JSONObject json : objs){
            if(json.getString("Value") == null)
                json.put("Value", "");
            list.add(json);
        }
        return list;
    }

}
