package math;

class Solution {
    public int programmers_minSquare(int[][] sizes) {
        int answer   = 0;
        int right = 0;
        int left  = 0;
        
        int rightMax = 0;
        int leftMax  = 0;
        for(int i = 0; i < sizes.length; i++) {
            for(int j = 0; j < 2; j++) {
                right = sizes[i][0] > sizes[i][1] ? sizes[i][0] : sizes[i][1];
                left  = sizes[i][0] < sizes[i][1] ? sizes[i][0] : sizes[i][1];
                
                if(rightMax < right) rightMax = right;
                if(leftMax  < left)  leftMax  = left;
            }
        }
        
        answer = rightMax*leftMax;
        return answer;
    }
}