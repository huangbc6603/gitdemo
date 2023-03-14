package org.example.utils;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class WorkFlowFilter {
	@JSONField(name="NodeName")
	private String nodeName;
	
	@JSONField(name="KeyVaue")
	private List<Node> nodes;
	
	private WorkFlowFilter() {
	}
	
	/**
	 * 创建filter
	 */
	public static WorkFlowFilter create(String nodeName) {
		WorkFlowFilter filter = new WorkFlowFilter();
		filter.nodeName = nodeName;
		if (filter.nodes == null)
			filter.nodes = new ArrayList<Node>();
		return filter;
	}
	
	/**
	 * 添加kv属性
	 */
	public WorkFlowFilter put(String key, String value) {
		Node node = new Node();
		node.key = key;
		node.value = value;
		nodes.add(node);
		
		return this;
	}
	
	public String getNodeName() {
		return nodeName;
	}
	
	public List<Node> getNodes() {
		return nodes;
	}

	public static class Node {
		@JSONField(name="Key")
		private String key;
		
		@JSONField(name="Value")
		private String value;

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", value=" + value + "]";
		}
	}
}
