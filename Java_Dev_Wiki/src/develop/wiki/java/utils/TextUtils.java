package develop.wiki.java.utils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {


	    public static void main(String[] args){
	        String priorStr = "http://1.tv.aiseet.atianqi.com/weixinact/Wechat/wx/scenecode?id=feedback&userid=1712862357&"+ filterUrlStr2("info=0600DF1400E081E142ED036B5663B695_5.0 Android") +"&pt=SNMAPP&pr=VIDEO&openid=&access_token=&appid=101161688&VN=1.8.0.74&VN_CODE=800&VN_BUILD=74&CHID=10009&guid=0600DF1400E081E142ED036B5663B695&qua=QV%3D1%26PR%3DVIDEO%26PT%3DSNMAPP%26CHID%3D10009%26RL%3D1920*1080%26VN%3D1.8.0%26VN_CODE%3D800%26SV%3D5.0%2BAndroid%26DV%3DHi3751V600%26VN_BUILD%3D74%26MD%3DSkyworth%2B8H84%2BE6200%26BD%3Dbigfish%26TVKPlatform%3D670603";
	        //String afterStr = priorStr.replace("\n", "");
	        //afterStr = afterStr.replace(" ", "");
	        
	        //String afterStr = filterUrlStr2(priorStr);
	        //float x = (float) ((45.5 + (float)77/2)/10);
	        String word = "hello";
	        String sentence = "am i going to say hello to this world?hello dd hello dd hello dd hello ddhello";
	        int pos = findPos("", sentence);
	        int count = countWord(word, sentence);
	        System.out.print("the first pos is-->" + pos);
	        System.out.println("the word count is-->" + count);
	    }
	    
	    
	    private static String filterUrlStr(String sourceUrl){

	        String regEx = "[\\s]";
	        Pattern pattern = Pattern.compile(regEx);
	        Matcher matcher = pattern.matcher(sourceUrl);
	        return matcher.replaceAll("").trim();

	    }
	    
	    private static String filterUrlStr2(String sourceUrl){
	        try {
	            sourceUrl = URLEncoder.encode(sourceUrl, "utf-8");
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        return sourceUrl;
	    }
	    
	    private static int findPos(String word,String sentence){
	    	return findPos(0, word, sentence);
	    }
	    
	    private static int findPos(int offset,String word,String sentence){
	    	if(offset < 0 ||null == word || null == sentence || "".equals(sentence) || "".equals(word)){
	    		return -1;
	    	}
	    	System.out.println("findPos offset-->" + offset + ";word-->" + word + ";sentence-->" + sentence.length());
	    	int count = 0;
	    	for(int i = offset; i < sentence.length();){
	    		System.out.println("compare sentecne pos-->" + i);
	    		for(int j = 0; j< word.length();){
	    			System.out.println("compare word pos-->" + j);
	    			if(i == sentence.length()){
	    				return -1;
	    			}
	    			if(word.charAt(j) == sentence.charAt(i)){
	    				i++;j++;
	    				if(j == word.length()){
	    					System.out.println("findPos compute time -->" + count);
	    					return i-j;
	    				}
	    			}else{
	    				if(j == 0){
	    					i++;
	    				}else{
	    					i -= j-1; j = 0;
	    				}
	    			}
	    			count ++;
	    		}
	    	}
	    	System.out.println("findPos time -->" + count);
	    	return -1;
	    }
	    private static int countWord(String word,String sentence){
	    	if(null == word || null == sentence || "".equals(sentence)){
	    		return 0;
	    	}
	    	int count = 0;
	    	for(int i = 0;i < sentence.length();){
	    		//System.out.println("count word offset pos -->" + i);
	    		int pos = findPos(i, word, sentence);
	    		//System.out.println("count word find pos -->" + pos);
	    		if(pos == -1){
	    			//System.out.println("count word break; count -->" + count);
	    			break;
	    		}else{
	    			count ++;
	    			//System.out.println("count word find; pos -->" + pos);
	    			i = pos + word.length();
	    		}
	    	}
	    	return count;
	    }
}
