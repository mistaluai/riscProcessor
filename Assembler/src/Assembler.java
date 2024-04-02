import Decoders.ITypeDecoder;

public class Assembler {



    Assembler() {

    }

    //Testing
    public static void main(String[] args) throws Exception {
        Assembler assembler = new Assembler();
        String instruction = "andi $1, $1, 2"; //0000000001001010
        ITypeDecoder rtd = new ITypeDecoder();
        System.out.println(rtd.decodeInstruction(instruction));
    }

}
