package bst;

import static org.junit.Assert.*;
import bst.BinarySearchTree;


import org.junit.Test;

public class BinaryTest {
	
	BinarySearchTree<String> bt = new BinarySearchTree<String>();
	String s1 = "a";
	String s2 = "b";
	String s3 = "c";
	String s4 = "a";
	String s5 = "d";
	
	
	
	@Test
	public void testheight(){
		if(bt.height() != 0){
			fail();
		}
		bt.add(s3);
		if(bt.height() != 1){
			fail();
		}
		bt.add(s1);
		if(bt.height() != 2){
			fail();
		}
		bt.add(s2);
		if(bt.height() != 3){
			fail();
		}
		bt.add(s4);
		
		//Testar dubbletter 
		if(bt.height() != 3){
			fail("Beräknade fel på dubblett");
		}
		
		//Testar då det blir 2 grenar
		bt.add(s5);
		if(bt.height() != 3){
			fail("Beräknade fel då det blev 2 grenar");
		}
		
		
	}
	
	
	@Test
	public void testadd() {
		bt.printTree();
		if(!bt.add(s3)){
			fail("Returnade false, då vi la in root");
		}
		if(bt.add(s3)){
			fail("Dubblett");
		}
		bt.add(s5);
		bt.add(s1);
		bt.add(s2);
		bt.printTree();
	}
	
	@Test
	public void testsize() {
		bt.add(s1);
		bt.add(s2);
		bt.add(s3);
		bt.add(s2);
		if(bt.size() != 3){
			fail("fel height");
		}
	}
	
	@Test
	public void testemptysize() {
		if(bt.size != 0){
			fail("fel resultat på empty tree");
		}
	}
	
	@Test
	public void testemptyheight() {
		if(bt.height() != 0){
			fail("fel resultat på empty tree");
		}
	}
	
	
	

}
