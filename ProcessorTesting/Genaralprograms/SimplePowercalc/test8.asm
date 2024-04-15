.data 
.text

addi $1, $1, 8
addi $2, $2, 2
addi $3, $3, 1

power:
beq $3, $1, exit
addi $3, $3, 1
sll $2, $2, 1
sw $2, 0($4)
j power

exit:
add $0, $0, $0
j exit
