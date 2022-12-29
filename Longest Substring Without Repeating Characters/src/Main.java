public class Main {

    public static void main(String[] args) {
        System.out.println( lengthOfLongestSubstring("ggububgvfk"));
    }
    static public int lengthOfLongestSubstring(String s) {
        StringBuilder stringBuilder=new StringBuilder();
        int max=0;
        for(char tmp:s.toCharArray()){
            if(stringBuilder.indexOf(String.valueOf(tmp))!=-1){
                if(max<stringBuilder.length()){
                    max=stringBuilder.length();
                }
                if(stringBuilder.indexOf(String.valueOf(tmp))==0){
                    stringBuilder.deleteCharAt(0);
                    stringBuilder.append(tmp);
                }else{
                    stringBuilder.delete(0,stringBuilder.indexOf(String.valueOf(tmp))+1);
                    stringBuilder.append(tmp);}
            }else{
                stringBuilder.append(tmp);
            }
            if(max<stringBuilder.length()){
                max=stringBuilder.length();
            }
        }
        return max;
    }
}