package main.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringWildCard {
	public static void main(String[] args) {
		
	    /*String line = "asdfasdf#{test1}zxcvzxcv#{text}zxcv%{asdf}";
	    String patternStr = "#\\{[a-z A-Z 1-9 -_.]+\\}|%\\{[a-z A-Z 1-9 -_.]+\\}";
	    
	    TextFileFormat fmt = new TextFileFormat(line, patternStr);
	    
	    System.out.println(fmt.findVarPosition().toString());
	    
	    Map<String, String> cvtList = new HashMap<>();
	    
	    for(String key : fmt.getTrgetVarNameList()) {
	    	cvtList.put(key, "1234");
	    }
		
	    System.out.println(fmt.convert(cvtList));*/
		
		
		/*boolean flag = Pattern.matches("^[a-zA-Z0-9]*$", "test"); 
		System.out.println(flag);*/
		
		/*String test = "(asdfasd|zxczxc)";
		String[] orKeywords = test.toLowerCase().substring(1, test.length()-1).split("\\|");
		System.out.println(orKeywords[0]);
		System.out.println(orKeywords[1]);*/
		
		/*String test = "name={name=test}";
		String key = test.substring(0, test.indexOf("="));
		String value = test.substring(test.indexOf("=")+1);
		System.out.println(key);
		System.out.println(value);*/
		
		/*Map<String, Map<String, String>> a = new HashMap<>();
		Map<String, String> b = new HashMap<>();
		b.put("test", "test1");
		a.put("a", b);
		
		System.out.println(a);
		
		
		Map<String, String> a1 = a.get("a");
		a1.put("test", "test2");
		
		System.out.println(a);*/
		
		/*Map<String, Object> test = new HashMap<>();
		
		if(test.containsKey("test")){
			System.out.println("있다.");
		}else {
			System.out.println("없다.");
		}*/
		
		
		
		/*String cellName = new CellAddress(1, 2).toString();
		System.out.println(cellName);*/

		
		
		/*ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		List<List<Object>> list = new ArrayList<>();
		
		List a = new ArrayList();
		a.add(1);
		a.add("test");
		a.add(1.5);
		
		list.add(a);
		list.add(a);
		list.add(a);
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Object result = engine.eval("var a = " + mapper.writeValueAsString(list) + "; var b = JSON.stringify(a); b");
			List b = mapper.readValue((String)result, ArrayList.class);
			System.out.println( b.toString() );
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}*/
		
//		String a = "test/asdf/zxcv/qwer";
//		System.out.println(a.split("asdf")[1]);
		
		/*String a = "CalcService.java";
		String[] b = a.split("\\.");
		
		System.out.println(b[0]);*/
		
//		long value = Long.parseLong("2020-05-08");
		
		
//		String test = "aaa\\test_1234\\bbb";
//		
//		System.out.println( Arrays.toString(test.split("test_[^\"]*\\\\")) );
		
		
		
		
		
//		Map<String, String> tt = new HashMap<>();
//		
//		tt.put("z", "");
//		tt.put("x", "");
//		tt.put("c", "");
//		
//		
//		System.out.println(tt.keySet().toString().matches("\\[.*\\]"));
//		System.out.println(tt.keySet().toString().substring(1, tt.keySet().toString().length() -1));
		
		
		
		
		/*int [] result = solution(new int[]{93,30,55}, new int[]{1,30,5});
		System.out.println(Arrays.toString(result));*/
		
//		System.out.println(Arrays.toString(solution(new int[]{100,99,98,97,96})));
		
//		System.out.println(Arrays.toString(solutionSort(new int[]{1,5,2,6,3,7,4}, new int[][]{{2,5,3,},{4,4,1},{1,7,3}})));
		
//		System.out.println(solutionMax(new int[] /*{6,10,2}*//*{3,30,34,5,9}*/{999,888,777,666,555,444,333,222,111,99,88,77,66,55,44,33,22,11,9,8,7,6,5,4,3,2,1}));

//		System.out.println(Arrays.toString(solutionVector(new int[][] {{1,1},{2,2},{1,2}})));
		
		
		
		String a = ", 14.14.14.14";
		
		
		System.out.println(a.replaceAll(",", ""));
		
	}

	public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        
        int topDay = 0;
        int index = -1;
        
        for(int i=0; i<progresses.length; i++){
            
            int mask = 100 - progresses[i];
            int calcDay = (int) Math.ceil((double) mask / speeds[i]);
            
            if(topDay < calcDay){
            	
            	int[] copy = answer;
            	answer = new int[copy.length + 1];
            	
            	for(int j=0; j<copy.length; j++)
            		answer[j] = copy[j];
            	
            	index++;
                topDay = calcDay;
                answer[index] = answer[index] + 1;
            }else {
                answer[index] = answer[index] + 1;
            }
            
        }
        
        return answer;
        
        // return list.stream().mapToInt(Integer::intValue).toArray();
        
    }
	
	public static int[] solution(int[] prices) {
	    int[] answer = {};
	    
	    for(int i=0; i<prices.length; i++){
	    	
	    	int find = 0;
	        
	    	for(int j=i; j<prices.length; j++){
	        	if(prices[i] > prices[j]) {
	        		find = j;
	        		break;
	        	}
	        }
	        
        	int[] copy = answer;
        	answer = new int[copy.length + 1];
        	for(int k=0;k<copy.length;k++) answer[k] = copy[k];
	        
        	if(find == 0)
        		answer[i] = prices.length - (i + 1);
        	else 
        		answer[i] = find - i;
        	
	    }
		return answer;
	}
	
	public static int[] solutionSort(int[] array, int[][] commands){
		
		int[] answer= {};
		
		for(int i=0;i<commands.length;i++){
			
			int s = commands[i][0];
			int e = commands[i][1];
			int k = commands[i][2];
			
			// 파싱
			int[] sep = new int[e-s+1];
			for(int j=(s-1), f=0; j<e; j++, f++) sep[f] = array[j];
			
			// 정렬
			for(int j=0;j<sep.length;j++){
				
				int min = j;
				for(int m=j+1;m<sep.length;m++) if(sep[min] > sep[m]) min = m; 
				
				int tmp = sep[j];
				sep[j] = sep[min];
				sep[min] = tmp;
				
			}
			
			// 데이터 셋팅
			int[] copy = answer;
			answer = new int[copy.length + 1];
			for(int g=0;g<copy.length;g++) answer[g] = copy[g];
			answer[answer.length-1] = sep[k-1];
			
		}
		
		return answer;
		
	}
	
	public static String solutionMax(int[] numbers){
		StringBuffer answer = new StringBuffer();
		
		int[] sort = new int[numbers.length];
		
		for(int j=0;j<numbers.length;j++){
			
			int maxi = j;
			
			for(int k=j+1;k<numbers.length;k++){
				
				
				String ns1 = (numbers[maxi] + "");
				String ns2 = (numbers[k] + "");
				
				if(Integer.parseInt(ns1 + ns2) < Integer.parseInt(ns2 + ns1)) maxi = k;
				
			}
			
			int tmp = numbers[j];
			numbers[j] = numbers[maxi];
			numbers[maxi] = tmp;
			
		}
		
		List<Integer> list = new ArrayList<>();
		Collections.sort(list, (a,b) ->{
			return -1;
		});
		
		for(int number : numbers){
			answer.append(number);
		}
		
		String sb = answer.toString();
		return sb.charAt(0) == '0' ? "0" : sb;
	}
	
    public static int[] solutionVector(int[][] v) {
        int[] answer = new int[2];

       answer[0] = compare(v[0][0], v[1][0]) ? v[2][0] : compare(v[0][0], v[2][0]) ? v[1][0] : v[0][0];
           
       answer[1] = compare(v[0][1], v[1][1]) ? v[2][1] : compare(v[1][1], v[2][1]) ? v[0][1] : v[1][1];

        return answer;
    }
    
    public static boolean compare(int a, int b){
        return a == b ? true : false;
    }
}
