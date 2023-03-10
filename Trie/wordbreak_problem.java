package Trie;

public class wordbreak_problem {
    static Node root = new Node();
    static class Node{
        Node[] children;
        boolean eow;
        public Node(){
            children = new Node[26];
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            eow = false;
        }
    }
    public static boolean search(String key){
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            int idx = key.charAt(i) - 'a';
            Node node = curr.children[idx];

            if (node == null) return false;
            if (i == key.length()-1 && node.eow==false) return false;
            curr = curr.children[idx];
        }
        return true;
    }
    public  static boolean wordbreak(String key){
        if (key.length() == 0) return true;
        for (int i = 1; i <= key.length(); i++) {
          String first = key.substring(0,i);
          String second = key.substring(i);
          if(search(first) && wordbreak(second)) return true;
        }
        return false;
    }
    private static void insert(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i)-'a';

            if (curr.children[idx] == null)
                curr.children[idx] = new Node();
            if (i == word.length()-1)
                curr.children[idx].eow = true;

            curr = curr.children[idx];
        }
    }
    public  static boolean startswith(String prefix){
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';

            if (curr.children[idx] == null) return false;
            curr = curr.children[idx];
        }
        return true;
    }
    public static int countNode(Node root){
        if (root == null) return 0;
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null)
                count+= countNode(root.children[i]);
        }
        return count+1;
    }
    public static String ans="";
    public static void longestWord(Node root,StringBuilder temp){
        if (root == null) return;
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null && root.children[i].eow == true){
                temp.append((char)(i+'a'));
                if (temp.length() > ans.length()){
                    ans = temp.toString();
                }
                longestWord(root.children[i],temp);

                temp.deleteCharAt(temp.length()-1);
            }
        }
    }
    public static void main(String[] args) {
        String words[] = {"the","a","there","their","any"};
        String key = "theany";
        for (int i = 0; i < words.length; i++) {
            insert(words[i]);
        }
        System.out.println("---------");
        System.out.println(wordbreak(key));

//        String pref = "app";
//        System.out.println(pref.charAt(1)-'a');

        String word[] = {"apple","app","mango","man","woman"};
        String prefix = "app";
        for (int i = 0; i < word.length; i++) {
            insert(word[i]);
        }
        System.out.println(startswith(prefix));
        ///////////////////////////////////

        String wordss[] ={"a","banana","app","appl","ap","apply","apple"};
        for (int i = 0; i < wordss.length; i++) {
            insert(wordss[i]);
        }
        longestWord(root, new StringBuilder(""));
        System.out.println(ans);


        ///////////////////// comment above to run below function and lines
        System.out.println("---------------------------");
        String str = "ababa";
        for (int i = 0; i < str.length(); i++) {
            String suffix = str.substring(i);
            //System.out.println(suffix);
            insert(suffix);
        }
        System.out.println(countNode(root));

    }


}
