package problem.solving.implementation.sixstars.CavityMap;

import java.util.Scanner;

public class RomanianSolution
{
    static String[] A=new String[666];
    static int j;
    static char[][] R= new char[666][666];
    static int xx[]= {1,-1,0,0};
    static int yy[]= {0,0,1,-1};

    public static void main(String[] Args)
    {
        Scanner f=new Scanner(System.in);
        int n=f.nextInt(),i;

        for(i=1;i<=n;++i)
        {
            A[i]= new String();
            A[i]=f.next();
        }

        for(i=1;i<=n;++i)
            for(j=0;j<n;++j)
                R[i][j]=A[i].charAt(j);

        for(i=2;i<n;++i)
            for(j=1;j<n-1;++j)
            {
                boolean okz=false;
                for(int t=0;t<4;++t)
                    if (R[i+xx[t]][j+yy[t]]>=R[i][j])
                        okz=true;

                if (okz==false)
                    R[i][j]='X';
            }

        for(i=1;i<=n;++i)
        {
            for(j=0;j<n;++j) System.out.print(R[i][j]);
            System.out.println();
        }
    }
}
