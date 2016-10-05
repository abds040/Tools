/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sshtools;

/**
 *
 * @author di3sdn
 */
public class Test {
    public static void  main(String args[]) {
        hexa(191026962);
    }
    public static void hexa(int num) {
        int m = 0;
        if( (m = num >>> 4) != 0 ) {
            hexa( m );
        }
        System.out.print((char)((m=num & 0x0F)+(m<10 ? 48 : 55)));
    }
}
