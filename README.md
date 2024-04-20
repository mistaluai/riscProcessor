# RISC Processor Documentation

**Key Features**: Simple Design, Assembler, Processor Simulator

[Github](https://github.com/mistaluai/riscProcessor)

[JavaDocs](https://mistaluai.github.io/riscProcessor/index.html)

[Explanation Video](https://drive.google.com/file/d/1riy4G78S8E8rITl4z5ljEgz_JgUMkf8l/view?usp=sharing)

## By

Luai Waleed Abdelkarim @mistaluai
Dai Ahmed Tag @daiahmed
Abdalla Mahmoud Ahmed @abdo7771

## Computer Engineering - Fayoum University

# RISC Architecture

RISC, or Reduced Instruction Set Computing, is a processor architecture known for its efficiency. Unlike its counterpart, Complex Instruction Set Computing (CISC), RISC processors focus on simpler instructions that execute faster. This design philosophy forms the foundation for our 16-bit RISC processor.

**Key Features of RISC**

- **Simplified Instructions:** RISC processors break down tasks into a set of basic, well-defined instructions, enabling faster execution and a streamlined design.

- **Register Focus:** These processors rely heavily on internal registers for data manipulation, reducing reliance on slower main memory access.

- **Multiple Registers:** RISC processors typically have a larger number of registers compared to CISC processors, allowing for faster access to frequently used data.

- **Compiler Optimization:** The simpler instruction set allows compilers to play a more significant role in optimising code for RISC processors.

**Benefits of RISC Architecture**

RISC processors offer several advantages, including:

- **Efficiency:** The focus on simpler instructions and registers leads to efficient processing.

- **Speed:** Faster instruction execution translates to overall performance improvement.

- **Lower Power Consumption:** The emphasis on internal data manipulation can reduce reliance on memory access, which can be power-hungry.

**Our 16-Bit RISC Processor Design**

Our design leverages the strengths of RISC architecture to create a 16-bit processor. This choice of bit size allows for:

- **Reduced Complexity:** Compared to a 32-bit design, a 16-bit processor offers a more compact and potentially lower power consumption footprint.

- **Targeted Applications:** This processor could be suitable for embedded systems or applications where a smaller memory footprint and lower power usage are critical.

By adopting the principles of RISC architecture, we aim to develop a 16-bit processor optimised for specific applications. This design approach offers a balance between performance and resource efficiency.

# Digital Circuit Design

## 2.1 Register File

The register file acts as a high-speed data storage unit, providing temporary holding grounds for frequently accessed operands during program execution.

**Architecture**

The register file is composed of two key subcircuits:

**Fundamental Register Unit (FRU):** Each FRU is a fundamental storage element containing a 16-bit register and an AND gate. The AND gate enables writing to a specific register while preserving the contents of others. Notably, **register 0 (R0)** is hardwired to always contain a zero value. This eliminates the need for a separate write operation to initialise it.

**Register Selector:** This unit consists of a decoder and tri-state buffers. The decoder interprets control signals to activate the appropriate tri-state buffer. The selected buffer's output is then driven onto the designated output bus (A or B).

**Configuration**

The register file provides:

- **Eight General-Purpose Registers (GPRs):** These 16-bit registers store operands and results during program execution, with **R0** permanently set to zero.

- **Dual Read Ports (A & B):** These ports allow concurrent access to different registers for read operations.

- **One Write Port (W): **This port allows the data on the write bus to be written to the desired register.

- **Write Enable Pin:** This signal controls writing to the selected register.

- **Register Write Bus:** This bus carries the data to be written to the selected register.

- **Debugging Outputs (Optional):** For debugging purposes, the design may include additional output pins providing direct access to the contents of specific registers (e.g., registers 1 to 7).

## 2.2 Program Counter Unit

The program counter (PC) unit plays a critical role in program execution by keeping track of the memory address of the instruction currently being fetched. It comprises the following components:

- **16-Bit Program Counter Register:** This register stores the address of the next instruction to be fetched.

- **Instruction Memory (ROM):** This memory stores the program instructions. The PC register serves as the address input for fetching instructions from the ROM.

- **Instruction Splitter:** This circuit splits the fetched instruction into its constituent parts (opcode, operands) based on the instruction format (R-type, I-type, J-type).

**Program Flow Control**

The PC unit facilitates program flow control through dedicated subcircuits and an adder:

#### **2.2.1 ****Jump Control Unit**

This unit calculates the new PC value for jump instructions (jumping and linking, jump register) using a single full adder and a multiplexer. This optimises resource utilisation.

#### **2.2.2 ****Branch Control Unit**

This unit employs Zero ALU flag, SLT instruction and a combination of tri-state buffers, multiplexers, and two full adders to manage conditional branching instructions (beq, bne, blt, bge).

#### **2.2.3 ****Automatic Incrementation**

The PC unit can be incremented by one during each clock cycle, ensuring sequential instruction fetching unless a jump or branch instruction alters the program flow.

#### **2.2.4 ****Control Unit Integration**

The control unit ultimately selects the new PC value (incremented PC, branch address, or jump address) based on the instruction type and execution status. This selection process ensures proper program flow management.


## **2.3 Video Memory**

The video memory subsystem provides a framebuffer for displaying visual information on an attached LED display.

**Memory Organisation:**

- **Size:** 256 bits

- **Organisation:** 16 registers of 16 bits each

**Data Representation:**

Each register holds the data corresponding to a single column of pixels on the LED display, either the pixel is on or off.

**Control Circuit:**

A dedicated control circuit governs writes to the video memory. This circuit ensures data integrity by:

- **Write Enable:** Conditional write access based on the current instruction opcode. This prevents unintended modifications during program execution.

- **Opcode-based Control:** A specific opcode (19), designated as "DRAW," enables writing to a targeted column in the video memory.

**DRAW Instruction:**

The "DRAW" instruction allows programmers to manipulate the LED display. It takes two operands:

- **Register source:** Selects the register containing the data to be displayed.

- **Column destination:** Specifies the target column on the LED display to be updated.

By combining arithmetic instructions for data manipulation with the "DRAW" instruction, programmers can create diverse shapes and animations, here is a small example of drawing a ball on the screen using predefined values in the RAM, Refer to the test case section for detailed examples.

This design provides a robust and efficient foundation for interacting with the LED display using the 16-bit RISC processor.

## 
## **2.4 ****ALU**

The ALU is the workhorse of the processor, responsible for performing various operations on data(logical, arithmetic, shifting). It acts like a high-speed calculator within the CPU.

- Arithmetic Unit (AU): Executes arithmetic operations such as addition, subtraction, set less than signed and unsigned.

- Logic Unit (LU): Performs bitwise logical operations like AND, OR, NOR, XOR.

- Shift Unit (SU): Enables shifting data elements (bits) left or right by a specified number of positions, either logical or arithmetic and it supports rotating right.

The ALU operates on two 16-bit operands, designated as operand A and operand B. To instruct the ALU on the specific operation to perform, a 4-bit operation code is provided. The chosen opcode determines which subcircuit is activated and the type of operation executed.

The ALU generates a single 16-bit output reflecting the result of the performed operation. Additionally, it includes a zero flag that indicates whether the operation resulted in a zero value. This flag plays a crucial role in conditional branching instructions within the processor's instruction set.

### **4.1 ****Logic Unit**

To choose the desired logical operation, the unit employs a multiplexer controlled by a 2-bit "Logic control" signal. This signal acts like a code that specifies which operation (AND, OR, XNOR, or NOR) the ALU should perform on the provided operands.

### **4.2 ****Arithmetic Unit**

The AU is a subcircuit within the ALU dedicated to performing arithmetic operations on 16-bit binary data. It supports the following instructions:

- Addition (ADD): Adds the values of operand A and operand B.

- Subtraction (SUB): Subtracts the value of operand B from operand A.

- Set Less Than (SLT): Compares operand A with operand B. If A is less than B, the result is 0001 and 0000 if otherwise.

- Set Less Than Unsigned (SLTU): Similar to SLT, this instruction compares operand A and B. If the unsigned value of A is less than to B, the result is 0001 and 0000 if otherwise.

##### **4.2.1 Operation Decoding**

A dedicated 2-bit "operation" signal from the control unit determines the specific arithmetic instruction to be executed by the AU. The decoding of this signal is handled by a simple combinational circuit within the ALU. This design offers efficient control over the AU's operations.

| 00 | add |
|---|---|
| 01 | sub |
| 10 | slt |
| 11 | sltu |

##### **4.2.2 ****1-Bit Arithmetic Unit**

The core functionality of the Arithmetic Unit (AU) hinges on a fundamental one-bit adder. This versatile circuit serves as the building block for all arithmetic operations within the AU. By cleverly cascading multiple one-bit adders along with carry logic, the AU can perform efficient addition and subtraction on 16-bit operands. The "operation" signal from the control unit dictates how these one-bit adders are utilised, enabling the AU to handle instructions like addition, subtraction, set less than (SLT), and set less than or unchanged (SLTU). This modular design using a one-bit adder not only simplifies the AU's implementation but also ensures efficient execution of core arithmetic operations.

**Inputs**:

- A: This represents the first 1-bit binary operand.

- B: This represents the second 1-bit binary operand.

- Cin (Carry-in): This optional input represents a carry bit from a lower-order addition (relevant in multi-bit adders).

**Outputs**:

- Sum (S): This output represents the sum of the two input bits (A and B) after considering the carry-in.

- Cout (Carry-out): This output represents the carry bit generated from the addition. This carry bit is typically propagated to the next higher-order addition stage in multi-bit adders.

##### **4.2.3 ****16-Bit Arithmetic Unit**

The 16-bit adder within the Arithmetic Unit (AU) is a prime example of efficient modular design. It tackles the addition of two 16-bit binary numbers by cleverly cascading a series of single-bit adders. These one-bit adders, the workhorses of the 16-bit adder, perform the fundamental addition operation on corresponding bits from the two operands. To ensure proper handling of the carry operation that can arise during addition, dedicated carry logic is incorporated. This carry logic propagates any carry generated by one-bit adder to the next bit position in the cascade, enabling accurate addition across all 16 bits. This modular approach using one-bit adders with carry logic not only simplifies the design of the 16-bit adder but also contributes to the overall efficiency of the AU in performing arithmetic operations.

##### **4.2.4 Zero Detection**

This circuit uses a single NOR gate to achieve a clever function. It acts like a zero flag detector. The zero flag is a signal that indicates when all the output bits (result bits) from an operation are 0.

##### **4.2.5 Set Less Than (SLT) Instruction**

The processor offers a dedicated "set less than" (SLT) instruction for efficient comparison of two operands. This instruction leverages a clever combination of the sign bit and overflow flag to determine the relative ordering of the operands.

During the execution of SLT, the ALU performs a subtraction operation on the two operands. The resulting sign bit (MSB) of the subtraction directly reflects the comparison outcome:

- Sign bit is 1 (negative): This indicates operand A is less than operand B.

- Sign bit is 0 (positive): This signifies operand A is either greater than or equal to operand B.

However, a potential edge case exists when subtracting two negative numbers with large magnitudes. In such scenarios, the subtraction can overflow, resulting in a positive value despite operand A being numerically larger. To account for this, the overflow flag, set during an overflow condition, is factored in.

The SLT instruction utilises an XOR (exclusive OR) operation between the sign bit and the overflow flag. This XOR operation produces a 1 in the least significant bit (LSB) only when:

- The sign bit is 1 (operand A is less than operand B) and

- There is no overflow (the subtraction result is accurate)

Therefore, the LSB of the SLT result reflects the comparison outcome, with 1 indicating operand A being less than operand B and 0 indicating otherwise. The remaining bits of the result are set to zero. This efficient logic using the sign bit and overflow flag enables the SLT instruction to perform comparisons without requiring a dedicated comparison unit, reducing hardware complexity.

##### **4.2.6 Set Less Than Unsigned (SLTU)**

The processor also provides a "set less than unsigned" (SLTU) instruction specifically designed for comparisons involving unsigned integers. Unlike the signed "set less than" (SLT) instruction, SLTU focuses solely on the magnitude of the operands.

During execution, the ALU performs a subtraction between the two operands. However, unlike SLT, the actual subtraction result is discarded. Instead, the SLTU instruction utilises the carry flag generated by the ALU's adder circuit.

- Carry flag set: This signifies that during the subtraction, a borrow operation (essentially adding one to the minuend) was required from the most significant bit (MSB) position. This condition indicates operand B is larger than operand A in an unsigned comparison.

- Carry flag not set: This implies there was no need to borrow from the MSB, suggesting operand A is either equal to or greater than operand B when considered as unsigned values.

To reflect this comparison outcome in the SLTU result, the carry flag is inverted using a logical NOT operation. This means the least significant bit (LSB) of the SLTU result is set to 1 only when the carry flag is set (operand B is larger than operand A). Otherwise, the LSB is set to 0. The remaining bits of the result are cleared to zero.

By leveraging the carry flag, the SLTU instruction performs efficient unsigned comparisons without requiring dedicated comparison hardware. This approach simplifies the design while maintaining functionality for unsigned integer comparisons.

### 4.3 Shifting Unit

The ALU's dedicated shifting unit empowers programmers to manipulate data at the bit level. It comprises four distinct subcircuits, each tailored for a specific type of shift operation:

##### **4.3.1 Shift Left Logical (SLL)**

This subcircuit performs a logical left shift on the operand. In an SLL operation, zeros are shifted into the least significant bit (LSB) positions, while the most significant bit (MSB) is discarded.

##### **4.3.2 Shift Right Logical (SRL)**

Similar to SLL, the SRL subcircuit executes a logical right shift. However, in an SRL, zeros are inserted into the MSB position, and the LSB is discarded.

##### **4.3.3 Shift Right Arithmetic (SRA)**

This subcircuit is designed for arithmetic right shifts. During an SRA, the sign bit (MSB) is replicated and propagated throughout the empty bit positions introduced by the shift. This operation preserves the sign of the original operand.

##### **4.3.4 Rotate Right (ROR)**

The ROR subcircuit performs a circular right shift. In a ROR operation, the discarded bit from the LSB position is shifted back into the MSB position, effectively rotating the bits of the operand to the right.

These versatile shifting operations enable programmers to perform tasks like multiplication by powers of two (SLL), division by powers of two (SRL/SRA), and bit extraction (ROR). The specific shifting subcircuit to be utilised is determined by the instruction set and decoded by the control unit within the ALU.

### 2.5 Data Memory

The processor is equipped with a 16-bit word-addressable data memory, enabling efficient random access of data during program execution. Each memory location can store a single 16-bit word. The address width of the memory is also 16 bits, allowing it to directly address 2^16 (65,536) unique memory locations.

The data memory features dedicated ports for communication:

- Data Input Port: Accepts data to be written to the selected memory location.

- Data Output Port: Provides data read from the selected memory location.

- Read Enable Signal: Controls read operations, ensuring data is only transferred out of memory when authorised.

- Write Enable Signal: Controls write operations, ensuring data is written into memory only when authorised.

- Address Port: Specifies the desired memory location for access.

This combination of features allows the processor to efficiently interact with data stored in memory, playing a vital role in program execution.


### 2.6 Bit Extender

The bit extender is a crucial component that facilitates efficient handling of immediate operands within the instruction set. This circuit takes a 5-bit immediate value present in an instruction and extends it into a full 16-bit operand usable by the ALU. The extension mode, either signed or unsigned, is determined by a dedicated control signal.

##### **6.1 Signed Extension**

In signed extension mode, the bit extender replicates the sign bit (MSB) of the 5-bit immediate value across all the higher-order bits (bits 5 to 15) of the resulting 16-bit operand. This approach preserves the signed nature of the original value, allowing for proper interpretation in arithmetic operations.

##### **6.2 Unsigned Extension**

Conversely, during unsigned extension, the bit extender fills the higher-order bits (bits 5 to 15) of the 16-bit operand with zeros. This maintains the unsigned nature of the immediate value, making it suitable for operations like bitwise manipulation and logical comparisons.

By providing this flexible extension capability, the bit extender empowers the instruction set to utilise compact 5-bit immediate values while ensuring they can be seamlessly integrated into 16-bit ALU operations. This contributes to efficient code size and program execution.

### 
### **2.7 Control Unit**

The control unit acts as the conductor of the RISC processor, orchestrating various operations based on the decoded instruction.

##### **7.1 Input Signals**

###### **7.1.1 Opcode**

This field within the instruction identifies the overall operation to be performed (e.g., add, subtract, load, store, branch).

###### **7.1.2 Function**

Depending on the instruction format (R-type, I-type), this field might provide additional details about the operation case of R-type.

##### 7.2 Output Signals

The control unit then generates a multitude of output signals to govern different aspects of the processor:

###### **7.2.1 ALU Control**

- **ALUSrc (Pin 1):** This signal dictates the source of the second operand for the ALU. A logic value of 1 instructs the ALU to utilise the immediate value from the instruction, while a 0 specifies a register value (typically rt in the instruction).

- **ALUop (Pin 2):** This 4-bit signal encodes the specific operation to be performed by the ALU. The control unit decodes the opcode and function fields to generate the appropriate ALUop value.

- **SignExtend (Pin 3):** This signal controls the extension mode for immediate values. A logic 1 enables sign extension, replicating the sign bit across all higher-order bits during operand preparation. Conversely, a 0 triggers zero extension, filling the higher-order bits with zeros.

###### **7.2.2 Register and Memory Control**

- **RegWrite (Pin 4):** This signal governs writes to the general-purpose registers. A logic 1 enables writing the result of an operation or loaded data to the designated register, while a 0 prevents any register updates.

- **RegDest (Pin 5 & 6):** These two bits determine the destination register for write operations. Depending on the instruction format (R-type or I-type) and the opcode, the control unit selects either the rd field (destination register) or the rt field (second operand register) as the write target.

- **RegWSrc (Pin 7 & 8):** These two bits specify the source data for writing to a register. A logic value of 00 indicates the ALU output should be written, while other values (depending on the specific instruction) might select the PC value (for load PC instructions) or the immediate value for specific operations.

- **Memory Read (Pin 9):** This signal initiates a read operation from memory. The control unit asserts this signal when an instruction requires data to be loaded from memory.

- **Memory Write (Pin 10):** Conversely, this signal triggers a write operation to memory. The control unit activates this signal when an instruction dictates storing data into memory.

###### **7.2.3 Program Counter Control**

- **PCSrc (Pin 11 & 12):** These two bits govern the source of the next program counter (PC) value. A value of 11 indicates a sequential increment (PC + 1), the default behaviour for most instructions. Other possibilities include using the branch address from a branch instruction or setting the PC based on a jump instruction (determined by the JumpOp signal).

- **BranchOp (Pin 13 & 14):** These two bits specify the type of branch operation to be performed if the branch condition is met. Different opcodes might utilise these bits to encode various branching conditions (e.g., equal, not equal, greater than).

- **JumpOp (Pin 15):** This signal controls the execution of jump instructions, to decide between jumping with immediate offset or jumping to a value in a specific register.

##### **7.3 Internal Design**

The control unit employs a modular approach, divided into three subcircuits for optimised functionality:

###### **7.3.1 ALU Control Circuit**

This circuit focuses on decoding the opcode and function fields to generate the appropriate ALU control signals (ALUSrc, ALUop, SignExtend).

###### **7.3.2 Register and Memory Control Circuit**

This subcircuit handles signals related to register write operations (RegWrite, RegDest, RegWSrc), memory access (Memory Read, Memory Write), and the destination register selection based on instruction format.

###### **7.3.3 Program Counter Control Circuit**

This circuit decodes the opcode and branch conditions to generate the PCSrc and BranchOp signals, ultimately influencing the next PC value and potential branching behaviour.

##### **7.4 Implementation**

The control unit within the RISC processor was designed using Logisim?s combinational analysis using predefined truth tables, and this truth tables yield the following equations

###### **7.4.1 Boolean Expressions**

| ALU Src | ~OP3 OP2 + OP3 ~OP2 + OP3 ~OP1 |
|---|---|
| signExtend | ~OP3 OP1 OP0 + OP3 OP2 |
| ALUOP0 | ~OP4 ~OP3 ~OP2 F0 + ~OP3 OP2 ~OP1 OP0 + OP3 ~OP2 OP0 + OP3 OP2 OP1 |
| ALUOP1 | ~OP3 ~OP2 F1 + ~OP3 OP1 ~OP0 + ~OP2 OP1 + OP4 |
| ALUOP2 | ~OP3 ~OP2 OP0 + ~OP3 OP1 OP0 + OP3 OP2 + OP4 |
| ALUOP3 | OP3 ~OP2 |
| RegWrite | ~OP4 ~OP3 ~OP1 + ~OP4 ~OP1 ~OP0 + ~OP3 OP2 + OP3 ~OP2 + OP4 ~OP3 OP1 ~OP0 + OP4 OP3 OP0 |
| RegDest0 | ~OP3 ~OP2 ~OP1 + OP4 ~OP3 OP0 + OP4 OP2 |
| RegDest1 | OP4 |
| RegWSrc0 | OP3 OP2 + OP4 ~OP3 ~OP2 OP1 OP0 |
| RegWSrc1 | OP4 |
| MemRead | OP3 OP2 ~OP1 ~OP0 |
| MemWrite | OP3 OP2 ~OP1 OP0 |
| PCSrc0 | ~OP1 + ~OP4 OP2 + OP3 ~OP2 + OP4 ~OP3 |
| PCSrc1 | ~OP4 ~OP3 + ~OP4 ~OP1 + ~OP2 OP1 + OP4 OP2 |
| BranchOP0 | OP0 |
| BranchOP1 | ~OP1 |
| JumpOP | ~OP3 |

###### **7.4.2 Circuits? Schematics**




### **2.8 The Single Cycle**

The single cycle circuit represents the culmination of the components explored in prior sections, forming the heart of the processor's execution engine. It leverages a carefully orchestrated sequence of operations within a single clock cycle to achieve each instruction's functionality.

This circuit integrates the capabilities of various sub-circuits:

- Data Path: This path facilitates the flow of data between registers, the ALU, memory, and other components. It includes the register file, the ALU, an immediate extender, and a multiplexer for selecting operands.

- Control Unit: This unit acts as the conductor, decoding the instruction and generating control signals to activate specific elements within the data path during each cycle.

- Memory Interface: This interface manages communication with external memory, handling load and store operations.

##### **2.8.1 Additional Instructions**

The single cycle circuit extends functionality beyond the core ALU operations by directly implementing specific instructions:

###### **8.1.1 Load/Store Instructions**

These instructions enable data transfer between registers and memory. The control unit activates the memory interface and data path accordingly, ensuring seamless communication during a single clock cycle.

###### **8.1.2 JAL and LUI**

JAL facilitates jumps to new program locations while preserving the return address. LUI allows efficient loading of constants into the upper 11 bits of a register. These instructions are typically implemented directly within the control unit's logic for immediate execution within a single cycle.

This single cycle design offers a balance between performance and simplicity. By executing each instruction within a single cycle, it avoids the complexities of multi-cycle execution. However, it might impose limitations on the instruction set complexity compared to some multi-cycle designs.

# RISC Assembler

Building a processor is just one piece of the puzzle. To interact with it and unleash its potential, we require a way to create instructions it understands.  This is where our custom RISC assembler comes into play.

This dedicated code editor offers a user-friendly interface for writing assembly programs. It streamlines the process by:

- **Simplified Instruction Writing:** The editor provides an environment where you can write your code using the defined RISC assembly instructions.

- **Assembly Functionality:** The core functionality lies in its assembler engine. This engine takes your assembly code as input and translates it line by line into machine code (hexadecimal format) that the processor can directly execute.

- **Data Preloading:** In addition to assembling code, the assembler provides the ability to preload memory with initial data values. This allows you to set up the memory state before program execution, making testing and debugging more efficient.

Detailed documentation in upcoming sections will delve deeper into the capabilities of the assembler, providing a comprehensive guide for writing and running programs on your RISC processor. Through the collaboration of the hardware and software components, we unlock the full potential of this custom-built system.

A more detailed documentation could be found at [JavaDocs](https://mistaluai.github.io/riscProcessor/index.html)

### **3.1 Assembling Process**

Our custom RISC assembler prioritises error-free code before translation. It employs a multi-step process to guarantee your program's integrity and efficient execution.

##### **3.1.1 Pre-processing Pass**

###### **3.1.1.1 Symbol Table Construction**

During the first iteration, the assembler meticulously scans the code, identifying all labels (symbolic names for memory locations). This information is used to create a symbol table, a vital reference point for future jumps and branches within the program.

###### **3.1.1.2 Data Predefinition**

The assembler also parses data directives in your code, extracting predefined values you intend to load into memory. These values are carefully stored in a dedicated data structure, ensuring they are readily available for memory initialization.

###### **3.1.1.3 Memory Initialization Generation**

After identifying data and labels, the assembler cleverly generates instructions to initialise memory with the predefined values. This ensures the memory state is set up correctly before program execution begins.

### **3.2 Assembly Pass**

##### **3.2.1 Instruction Decoding**

Once the groundwork is laid, the assembler embarks on the core assembly process. It utilises a specialised approach, employing five distinct decoders tailored to handle different instruction categories within the RISC architecture. Each decoder translates a specific instruction category into its corresponding machine code representation.

##### **3.2.2 Regular Expression Parsing**

To extract crucial details from instructions (like register numbers or immediate values), the assembler leverages regular expressions. These powerful patterns ensure accurate parsing of instruction syntax, preventing errors during translation.

##### **3.2.3 Custom Data Structure Operations**

Throughout the decoding process, the assembler relies on a custom data structure designed to manage various operations needed for machine code generation. This structure streamlines the translation process, ensuring efficient handling of all the steps involved in converting assembly instructions into their equivalent machine code.

By combining meticulous pre-processing with robust decoding and parsing techniques, the RISC assembler empowers you to write assembly code with confidence, knowing it will be accurately translated into machine code ready for execution on your processor.

### **3.3 Exception Handling**

To ensure reliable program execution, our RISC processor incorporates an exception handling mechanism. This mechanism identifies and addresses two primary types of exceptions that can arise during program execution:

##### **3.3.1 Range Exceptions**

These exceptions occur when immediate values or offsets specified in instructions fall outside the permissible range. For instance, an immediate value intended for a signed operation might be too large to be represented within the allowed number of bits. When such an out-of-range condition is detected, the processor triggers a range exception.

##### **3.3.2 Syntax Exceptions**

Syntax errors within the assembly code itself can lead to these exceptions. These errors might involve typos in instruction keywords, incorrect operand usage, or missing elements within an instruction. The assembler, during the pre-processing phase, can definitely identify all syntax errors.

By incorporating exception handling, the RISC processor enhances robustness and simplifies debugging. It gracefully handles unexpected situations, preventing program crashes and aiding in the identification of errors within the code.

### **3.4 User Interface**

The RISC assembler goes beyond simple code translation. It boasts a user-friendly interface designed to enhance assembly language programming experience.

##### **3.4.1 File Management**

- A dedicated menu bar grants easy access to file management functions. You can effortlessly save your assembly code as you write it, open previously saved files for editing, and save the assembled program (machine code) for loading onto the processor.

- Additionally, the assembler allows you to save logs generated during the assembly process. These logs, which detail the assembly steps and any encountered errors, become invaluable tools for debugging and tracing program behaviour once loaded onto the processor.

##### **3.4.2 Build Process**

A dedicated "Build & Assemble" menu option triggers the assembly process. Simply write your code, click "Build & Assemble," and the assembler translates your assembly instructions into machine code ready for execution.

##### **3.4.3 Additional UI Features**

- The user interface cleverly integrates the symbol table, displaying it prominently for easy reference. This allows you to quickly check label definitions and memory locations while writing your code.

- To further enhance readability and error prevention, the code text area offers syntax highlighting. This feature visually differentiates keywords (instructions, registers) from data values and comments, making it easier to identify potential syntax issues within your code.

This combination of features empowers us to write, assemble, and debug our RISC assembly programs efficiently. The intuitive interface streamlines the development process, allowing you to focus on crafting test programs logic with confidence.

# ALU Testing Automator

To ensure the robustness and functionality of your ALU design, we've developed a specialised ALU testing automator software. This software empowers conducting comprehensive testing efficiently, saving valuable time and effort.

##### **4.1 Randomised and Targeted Testing**

###### **4.1.1 Random Input Generation**

The software can generate a multitude of random inputs (operands) for the ALU. This approach helps identify potential errors across various input values, ensuring broad coverage of the ALU's functionality.

###### **4.1.2 Random Operation Selection**

Complementing the random input generation, the software can also randomly select ALU operations from the supported instruction set. This further expands the testing scope by exercising different ALU functionalities.

###### **4.1.3 Expected Output Calculation**

 For each generated input and operation combination, the software acts as a **golden model**, simulating the ALU and calculating the expected output based on the correct functionality.

##### **4.2 Seamless Integration and Feedback**

###### **4.2.1 Logisim Compatibility**

The software is designed to integrate seamlessly with Logisim, a digital logic design and simulation tool. It allows you to easily configure your ALU design within Logisim and connect the software's generated inputs and operation signals. These signals can be loaded into ROMs within your Logisim design.

###### **4.2.2 Output Verification**

Once the simulation in Logisim is complete, the software can access the output values stored in a designated RAM within your Logisim design. This allows for efficient comparison between the actual ALU output and the software-calculated expected output.

###### **4.2.3 Comprehensive Feedback**

The software then analyses the results and provides detailed feedback. It highlights any discrepancies between the actual and expected outputs, pinpointing potential errors within your ALU design.

##### **4.3 Flexibility and Efficiency**

###### **4.3.1 Bulk Testing**

This powerful software allows you to conduct hundreds of tests within a matter of minutes. This expedites the testing process, enabling you to quickly identify and address any issues within your ALU design.

###### **4.3.2 Customizable ALU Signals**

To accommodate different ALU implementations, the software is designed with flexible ALU signal configuration. You can easily adjust the signal definitions within the software to match your specific ALU design, ensuring compatibility across various hardware configurations.

###### **4.3.3 Targeted Testing Option**

Beyond random testing, the software also offers the option to select specific instructions for targeted testing. This allows you to focus on particular ALU functionalities you might consider more critical or error-prone.

##### **4.4 CSV Output**

To facilitate further analysis and record keeping, the software generates a comprehensive CSV (comma-separated values) file after each testing session. This file meticulously documents all the test cases, including the generated random inputs, selected operations, calculated expected outputs, and the actual ALU outputs from the simulation. This detailed log allows you to track your testing progress, identify trends, and revisit specific test cases for further examination.

By combining random and targeted testing capabilities with seamless Logisim integration, informative feedback, and detailed record keeping through CSV output, the ALU testing automator software becomes a valuable tool in our ALU design verification process. It streamlines testing, saves time, and empowers you to build confidence in the correctness of your ALU implementation.

# Processor Simulator & Verificator

The processor simulator serves as a cornerstone for testing, debugging, and understanding the intricate execution process within the custom RISC processor. It acts as a virtual counterpart, meticulously simulating the processor's behaviour on a software level.

### **5.1 Golden Model for Verification**

This simulator transcends a simple execution environment. It embodies the "golden model" of the processor's functionality. By faithfully replicating the behaviour of the actual hardware design, it provides a reliable reference point for validation. During program execution, the simulator's behaviour can be compared to the documented behaviour of the processor, ensuring correctness and identifying any potential discrepancies. This rigorous comparison aids in debugging hardware issues or software bugs within your programs.

### **5.2 Software Realisation of Hardware Components**

At its core, the processor simulator is a comprehensive software implementation of every digital circuit and component constituting your RISC processor. It meticulously replicates the functionality of the control unit, the ALU, the register file, memory interface, and other essential components. Additionally, it encompasses the complete instruction set architecture (ISA) of the processor, ensuring accurate handling of all supported instructions.

### **5.3 Organised Instruction Execution**

To orchestrate the simulation process, the simulator utilises a well-defined data structure. This structure serves as a central repository for the program instructions you intend to execute. The simulator fetches instructions one by one from this data structure, mimicking the fetch cycle of the actual processor. Each instruction is then meticulously executed on the software-simulated components, replicating the behaviour of the hardware during program execution.

### **5.4 Customised Execution Pace**

The simulator empowers you to tailor the execution pace to your needs. You can choose to run the entire program at once, observing the overall behaviour from start to finish. Alternatively, for more in-depth debugging and analysis, the simulator allows you to execute the program cycle by cycle. This granular control allows you to step through each instruction, inspect the state of the processor after each cycle (register values, memory contents), and gain a deeper understanding of the program's execution flow.

### **5.5 Comprehensive User Interface for Visualization**

The processor simulator offers a user-friendly interface to visualise the execution process and program state. This interface typically includes:

- **Instruction Table:** This table displays all the program instructions in a clear and organised manner. It showcases the index of each instruction within the program memory, its corresponding hexadecimal machine code representation, and potentially the original assembly code for readability (if the simulator allows loading assembly files and disassembling them).

- **Register File View:** A dedicated section displays the contents of all the processor's registers in real-time. This allows you to monitor how register values change throughout program execution and identify any unexpected modifications.

- **Program Counter (PC):** The current value of the program counter (PC) is prominently displayed, indicating the address of the next instruction to be fetched. This visualisation helps you track the program's progress and pinpoint the location of execution if encountering issues.

- **Memory View:** A section of the interface showcases the contents of the processor's memory. You can observe how data is loaded, stored, and accessed during program execution.

- **Symbol Table:** If your simulator supports symbolic debugging, the symbol table might be integrated into the interface, providing a quick reference for memory locations associated with symbolic labels used in your assembly code.

By combining a software-based golden model with a well-structured instruction execution engine, customizable execution pace, and a comprehensive user interface, the processor simulator becomes an invaluable tool for testing, debugging, and understanding the intricate inner workings of the RISC processor. It empowers us to confidently validate our hardware design, identify and rectify software bugs, and gain a deeper appreciation for the program execution process within your custom processor.

It is **highly encouraged** to visit the [JavaDocs](https://mistaluai.github.io/riscProcessor/index.html) of these softwares if you want to build on them or utilise them to another custom single cycle processor.

# Testing and Verification

### 6.1 ALU Test

We utilised the testing automator to test our ALU, we have conducted 128 tests on our ALU and it passed all of them.

For more tests check our github.

### 6.2 Test Programs

#### 6.2.1 **Sum Array**

Program that initialises an array of 30 and sums its element and returns it in R4

**Expected vs Actual Registers**


Expected vs Actual Memory


# 

#### 6.2.2 Register Manipulation and Function Call (Sent by T.A.)

The code tracing could be found in an excel sheet in our project files.

**Expected vs Actual**


#### 
#### 6.2.3 Minimum Value Finder

This program takes 3 values and returns the smallest value.

**Expected vs Actual**

#### 6.2.4 Average Value Calculation

Takes 4 numbers and return their average

Expected vs Actual

#### 6.2.5 Power Calculation

Simple program that calculates 2 power of 8

**Expected vs Actual**



#### 6.2.6 Values Swapper

Simple program that swaps the values in two registers

**Expected vs Actual**

# 

# 

# 

# 

# 

# 

# 

# 

# 

#### 6.2.7 Bouncing Ball (Graphics)

Simple program that draws an animation of  a bouncing ball on the screen

The next two frames play in loop with each other

# 

#### 6.2.8 Ping Pong (Graphics)

This frames continue to play and the two bars keep hitting the ball and move it between each others

# Work Details

### 7.1 Tasks Per Name

#### Dai Ahmed Tag

1. Logic Unit

2. ALU Integration (in a meeting)

3. ALU Control Unit Signals & Implementation (in a meeting)

4. Single Cycle implementation (in a meeting)

5. Assembler & Simulator Testing and Verification

6. Wrote 3 Test Programs and various test codes

7. Test Programs Documentation

8. Project Video Voice Over

#### Abdalla Mahmoud Ahmed

1. Arithmetic Unit

2. Branch control unit

3. Data Control Unit Signals & Implementation (in a meeting)

4. Single Cycle implementation (in a meeting)

5. Assembler & Simulator Testing and Verification

6. Wrote 2 Test Programs and various test codes

7. Documentation Review

8. Project Video Voice Over

#### Luai Waleed Abdelkarim

1. Register File

2. Shifting Unit

3. Jump control unit

4. Instruction Splitter

5. VRAM

6. Program Counter Control Unit Signals & Implementation (in a meeting)

7. Single Cycle implementation (in a meeting)

8. Wrote 1 Test Program

9. Developed the Assembler, Simulator, Testing Automator.

10. Project Video Editing and Voice over

### 7.2 Workflow & Softwares

##### **7.2.1 GitHub**

In our development process, we leverage GitHub's branching and version control to streamline our workflow. This ensures stability through organised code & circuit versions and facilitates collaboration by enabling efficient branching.

##### **7.2.2 Logisim**

In our design process, we used Logisim to create a virtual environment for building and testing a 16-bit RISC processor.  We leveraged Logisim's library of digital logic components to construct the core elements, including the Arithmetic Logic Unit (ALU), register file, and control unit.  This software's visual interface streamlined the process by allowing us to connect these components, and meticulously test the processor's functionality at each stage.

##### **7.2.3 IntelliJ Idea**

We utilised IntelliJ IDEA as our Integrated Development Environment (IDE) to develop the assembler, simulator, and tester for our 16-bit RISC processor.  Java served as the programming language, providing a robust and object-oriented foundation for the project.  For the user interface, we leveraged Java Swing, a GUI toolkit within Java, to create a user-friendly experience for interacting with the assembler, simulator, and tester.  IntelliJ's features, such as code completion, debugging tools, and project management, streamlined the development process, while Java's strengths in code organisation and platform independence ensured a well-structured and portable application.  Java Swing's visual components allowed us to design intuitive interfaces for each tool, making it easy for users to assemble instructions, simulate program execution, and test the functionality of the RISC processor.

##### **7.2.4 Discord**

For uninterrupted, focused discussions during long meetings, we use a dedicated voice channel on our Discord server.  Here, participants can drop in and out as needed, reducing bandwidth usage compared to video calls. Discord prioritises clear audio and allows for side discussions in text chat without disrupting the main flow. This fosters a flexible and efficient meeting environment.

### **7.3 Meetings**

1. 2nd March, 8:30Pm: Orientation Meeting and general discussion about mips ISA and assembly

2. 5th March, 8:30Pm: Follow up meeting on the mips assembly and problem solving

3. 12th March, 9:30pm: Follow up meeting on the problem solving

4. 28th March, 10pm: Discussion about the workflow and training on Git

5. 4th April, 1pm: Dividing the project into small tasks and initialising the git structure

6. 7th April, 9:30Pm: ALU Subcircuits review and refactoring

7. 9th April, 9:00Am: ALU Integration and Jump Control Design

8. 11th April, 10:00Am: Branch Control and Single Cycle Integration

9. 11th April, 10:00pm: Control Unit Design and Implementation

10. 13th April 9:00Am: Assembler and Simulator Review and Discussion about Testing workflow

11. 15th April 5:00Pm: Fixing merge issues in github, VRAM review, Discussion about documentation

12. 17th April 9:30Pm: Testing Documentation and reviewing the rest of the documentation

13. 18th April 9:00Pm: Documentation review and video recording

### **7.4 Project Folder Structure**

1. RISCProcessor(Folder) contains the logisim files with the main circuit in the SingleCycle Folder

2. RISCAssembler(Folder) contains the source code of the assembler, testing automator and processor simulator, all as a one java module.

3. ProcessorTesting(Folder) contains 3 different types of tests, simple test codes for testing instructions functionality, and more complex test programs that we have included some of them in this document, and graphics showcasing codes.

