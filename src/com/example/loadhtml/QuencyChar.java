package com.example.loadhtml;

class QuencyChar {

	// 解析html的方法（自己写了个封装）
	public static String loopString(String URL) {
		String str = "";
		if (URL.indexOf("<li>") != -1 && URL.indexOf("</li>") != -1) {
			str += "\n"
					+ URL.substring(URL.indexOf("<li>") + 4,
							URL.indexOf("</li>")) + "\n";
			str += loopString(URL.substring(URL.indexOf("</li>") + 5));
		} else {
			return str;
		}
		return str;
	}

}
