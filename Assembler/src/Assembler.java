import Decoders.LoadStoreDecoder;

public class Assembler {



    Assembler() {

    }

    //Testing
    public static void main(String[] args) throws Exception {
        Assembler assembler = new Assembler();
        String instruction = "lw $1, 10($2)"; //0110001010010001
        LoadStoreDecoder lsd = new LoadStoreDecoder();
        System.out.println(lsd.decodeInstruction(instruction));
    }

}
