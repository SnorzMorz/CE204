package trees;

public class CodingBat {
    public int countHi(String str) {
        if(str.length() <= 2)
            if(str.equals("hi"))
                return 1;
            else
                return 0;

        if(str.substring(0, 2).equals("hi"))
            return 1 + countHi(str.substring(1, str.length()));
        else
            return countHi(str.substring(1, str.length()));
    }

    public String changeXY(String str) {
        if(str.length() == 1)
            if(str.charAt(0) == 'x')
                return "x";
            else
                return str;


        if(str.charAt(0) == 'x')
            return "y" + changeXY(str.substring(1, str.length()));
        else
            return String.valueOf(str.charAt(0)) + changeXY(str.substring(1, str.length()));
    }

    public boolean array220(int[] nums, int index) {
        if(index + 1 < nums.length){
            if(nums[index] == (nums[index + 1]))
                return true;
            else
                return array220(nums, index + 1);
        }

        return false;
    }

    public int countHi2(String str) {
        if(str.length() <= 2)
            return 0;


        if(str.substring(1, 3).equals("hi") && str.charAt(0) != 'x')
            return 1 + countHi2(str.substring(1, str.length()));
        return countHi2(str.substring(1, str.length()));
    }

    public static void main (String[] args) {
        CodingBat bat = new CodingBat();
        System.out.println(bat.countHi("ihihihihihi"));
        System.out.println(bat.array220(new int[]{1, 2, 20}, 0));
        System.out.println(bat.countHi2("ahixhi"));
        }
    }

