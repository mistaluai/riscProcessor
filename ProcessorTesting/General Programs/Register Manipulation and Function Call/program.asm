.data
first: .word 1 0
second: .word 1 1
third: .word 10 2
fourth: .word 17162 60
fifth: .word 29506 61
.text
lui 900
addi $5, $1, 13
xor $3, $1, $5
lw $1, 0($0)  #reg[1] <- mem[0]
lw $2, 1($0) #reg[2] <- mem[1]
lw $3, 2($0) #reg[3] <- mem[2]
addi $4, $4, 10
sub $4, $4, $4
L2:
add $4, $2, $4
slt $6, $2, $3 
beq $6, $0, L1
add $2, $1, $2 
beq $0, $0, L2
L1:
sw $4, 0($0) #mem[0] <- reg[4]
jal func 
sll $3, $2, 6
ROR $6, $3, 3
halt:
beq $0, $0, halt #program is over, keep looping back to here
func:
or $5, $2, $3
lw $1, 0($0)
lw $2, 5($1)
lw $3, 6($1)
and $4, $2, $3 
sw $4, 0($0) #mem[0] <- reg[4]
Jr $7
