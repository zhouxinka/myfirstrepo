package com.example.testEnum;
public class Test{
    public enum  Color{
        RED("红色","1"),GREEN("绿色","2"),BLANK("黑色","3");
        private String name;
        private String index;

        private Color(String name, String index) {
            this.name = name;
            this.index = index;
        }

        public  static String getName(String index){
            for (Color value : Color.values()) {
                if(index.equals(value.getIndex())){
                    return value.name;
                }

            }
            return null;
        }
        // get set 方法
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getIndex() {
            return index;
        }
        public void setIndex(String index) {
            this.index = index;
        }
    }
}


