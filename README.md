# Pipelined MIPS Processor Implementation
Simple 16-bit RISC processor with seven 16-
bit general purpose registers: R1 through R7. R0 is hardwired to zero and cannot be written,
we are left with seven registers. There is also one special-purpose 16-bit register, which is the
program counter (PC). All instructions are only 16 bits. There are three instruction formats,
R-type, I-type, and J-type


## Instruction Formats
#### R-type format
5-bit opcode (Op), 3-bit destination register Rd, and two 3-bit source registers Rs & Rt and 2-
bit function field F
| Op5 |F2| Rd3 | Rs3 | Rt3 |
|----|---|---|---|---|

#### I-type format
5-bit opcode (Op), 3-bit destination register Rd, 3-bit source register Rs, and 5-bit immediate
Op5 | Imm5 | Rs3 | Rt3
|----|---|---|---|

#### J-type format
5-bit opcode (Op) and11-bit Immediate

Op5 | Imm11
|----|---|


## Register Use
For R-type instructions, Rs and Rt specify the two source register numbers, and Rd
specifies the destination register number. The function field F can specify at most four
functions for a given opcode. We can reserve several opcodes for R-type instructions.
For I-type instructions, Rs specifies a source register number, and Rt can be a second
source or a destination register number. The immediate constant is only 5 bits because of the
fixed-size nature of the instruction. The size of the immediate constant is suitable for our
uses. The 5-bit immediate constant can be signed or unsigned depending on the opcode. The
immediate constant is signed (range is -16 to +15), except for shift and rotate instructions
(range is 0 to 31).
The J-type format is used by J (jump), JAL (jump-and-link), and for LUI instructions.
The 11-bit immediate is used for PC-relative addressing and constant formation.

## Register File
Implement a Register file containing seven 16-bit registers R1 to R7 with two read ports and
one write port.
R0 is hardwired to zero.
