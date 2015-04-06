import java.util.Stack;
import java.text.DecimalFormat;

public class dtwclass{
    public static void dtw(double a[],double b[],double dw[][], Stack<Double> w){
        // a,b - the sequences, dw - the minimal distances matrix
        // w - the warping path
        int n=a.length,m=b.length;
        double d[][]=new double[n][m]; // the euclidian distances matrix
        for(int i=0;i<n;i++)
        for(int j=0;j<m;j++)d[i][j]=Math.abs(a[i]-b[j]);
        // determinate of minimal distance
        dw[0][0]=d[0][0];
        for(int i=1;i<n;i++)dw[i][0]=d[i][0]+dw[i-1][0];
        for(int j=1;j<m;j++)dw[0][j]=d[0][j]+dw[0][j-1];
        /*
        for(int i=1;i<n;i++) {
            for(int j=1;j<m;j++) {
                if(dw[i-1][j-1]<=dw[i-1][j]) {
                    if(dw[i-1][j-1]<=dw[i][j-1])dw[i][j]=d[i][j]+dw[i-1][j-1];
                    else dw[i][j]=d[i][j]+dw[i][j-1];
                }
                else {
                    if(dw[i-1][j]<=dw[i][j-1])dw[i][j]=d[i][j]+dw[i-1][j];
                    else dw[i][j]=d[i][j]+dw[i][j-1];
                }
            }
        }
        */
        int i=n-1,j=m-1;
        double element=dw[i][j];
        // determinate of warping path
        w.push(new Double(dw[i][j]));
        do{
            if(i>0&&j>0)
            if(dw[i-1][j-1]<=dw[i-1][j])
            if(dw[i-1][j-1]<=dw[i][j-1]){i--;j--;} else j--;
            else
            if(dw[i-1][j]<=dw[i][j-1])i--; else j--;
            else if(i==0)j--; else i--;
            w.push(new Double(dw[i][j]));
        }
        while(i!=0||j!=0);
    }
    
    public static void main(String[] args){
        double ar[] =  new double[] {3,-13,14,-7,9,-2};
        double br[] =  new double[] {-2,10,-10,15,-13,20,-5,14,2};
        double dwr[][] = new double[6][9];
        Stack<Double> wr = new Stack<Double>();
        dtw(ar,br,dwr,wr);
        String resultdwr = "";
        DecimalFormat decimalformat = new DecimalFormat("#");
        for(int i=0;i<6;i++){
            for(int j=0;j<9;j++)
                resultdwr = resultdwr + "\t" + decimalformat.format(dwr[i][j]); 
            System.out.println(resultdwr);
            resultdwr = "";
        }
    }
}

