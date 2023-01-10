package dna;

import java.util.ArrayList;

public class DNA {

private String sequences ;
private String noJunk;
private int amountJunk;
public DNA (String initSequences){
    sequences=initSequences ;
    noJunk=deleteJunk();
}

private String deleteJunk(){
String noJunk="";
for (int i=0; i<sequences.length();i++){
   if(sequences.charAt(i)=='A'||sequences.charAt(i)=='C'||sequences.charAt(i)=='T'||sequences.charAt(i)=='G'){
    noJunk+=sequences.charAt(i);
   }
}
amountJunk=sequences.length()-noJunk.length();
return noJunk;
}

public boolean isProtein(){
    if (!noJunk.substring(0, 3).equals("ATG")){
        return false;
    }
    if (!noJunk.substring(noJunk.length()-3).equals("TAA")&&!noJunk.substring(noJunk.length()-3).equals("TAG")&&!noJunk.substring(noJunk.length()-3).equals("TGA")){
        return false;
    }
    if (noJunk.length()<15){
        return false;
    }
    if ((nucleotideCount('C')*111.103+nucleotideCount('G')*151.128)/totalMass()<0.3){
        return false;
    }
    return true;
}

public int nucleotideCount(char c) {
    int numberTimes=0;
    for (int i=0;i<noJunk.length();i++){
       if(noJunk.charAt(i)==c) {
        numberTimes+=1;
       }
    }
    return numberTimes;
}

public double totalMass(){
    double totalMass=0;
    totalMass+=nucleotideCount('A')*135.128;
    totalMass+=nucleotideCount('C')*111.103;
    totalMass+=nucleotideCount('G')*151.128;
    totalMass+=nucleotideCount('T')*125.107;
    totalMass+=amountJunk*100 ;
    return totalMass;
}



public ArrayList codonSet(){
    ArrayList<String> allCodons = new ArrayList<String>();
    for (int i=0; i<noJunk.length();i+=3){
        allCodons.add(noJunk.substring(i,i+3));
    }
    return allCodons ;
}


public String mutateCodon(String originalCodon, String newCodon){
    String newDna=noJunk; 
    int i=newDna.indexOf(originalCodon);
    String partOne=noJunk.substring(0, i);
    String partTwo=noJunk.substring(i+3, noJunk.length());
    newDna=partOne+newCodon+partTwo;
    return newDna;
}

    
public String sequence(){
return noJunk;
}

}
