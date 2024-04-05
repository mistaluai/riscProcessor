import Decoders.ITypeDecoder;

public class Assembler {



    Assembler() {

    }

    //Testing
    public static void main(String[] args) throws Exception {
        Assembler assembler = new Assembler();
        String instruction = "addi $1, $2, "; //0110001010010001
        ITypeDecoder lsd = new ITypeDecoder();
        System.out.println(lsd.decodeInstruction(instruction));
    }

}
