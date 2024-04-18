.data 
.text

#initialize registers
addi $1, $1, 8
addi $2, $2, 2
addi $3, $3, 1

power:
beq $3, $1, exit #exit if calculates the desired power
addi $3, $3, 1 #increment counter
sll $2, $2, 1 #multiply by 2 
j power

exit:
add $0, $0, $0
j exit
