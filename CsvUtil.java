package com.camilion.custom.integration.fspm.util.ext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvUtil
{
  public static StringBuffer toCsvFromListOfMaps(List<Map<String, String>> String... headers) {
    StringBuffer result = new StringBuffer();
    result.append(toCsvFromStringList(headers));
    result.append('\n');
    
    for (Map<String, String> map : mapList) {
      
      List<String> row = new ArrayList<String>();
      for (String h : headers) {
        
        String value = (String)map.get(h);
        if (value == null)
        {
          value = "";
        }
        row.add(value);
      } 
      result.append(toCsvFromStringList(row));
      result.append('\n');
    } 
    
    return result;
  }
  
  public static StringBuffer toCsvFromStringList(List<String> sa) {
    String[] sa2 = new String[sa.size()];
    sa2 = (String[])sa.toArray(sa2);
    return toCsvFromStringList(sa2);
  }
  
  public static StringBuffer toCsvFromStringList(String[] sa) {
    StringBuffer result = new StringBuffer();
    boolean first = true;
    for (String s : sa) {
      
      if (!first)
      {
        result.append(',');
      }
      first = false;
      result.append(toCsvFromString(s));
    } 
    return result;
  }
  
  public static StringBuffer toCsvFromString(String s) {
    StringBuffer result = new StringBuffer();
    String s2 = s.replaceAll("\"", "\"\"");
    result.append('"');
    result.append(s2);
    result.append('"');
    return result;
  }

  
  public static StringBuffer toCsvFromString(StringBuffer s) {
    if (s == null)
    {
      return null;
    }
    
    int i = 0;
    while (i < s.length()) {
      
      char c = s.charAt(i);
      if (c == '"') {
        
        s.insert(i, '"');
        i++;
      }
      else if (c == '\n') {
        
        s.deleteCharAt(i);
        s.insert(i, "\\n");
        i++;
      } 
      i++;
    } 
    return s;
  }
}