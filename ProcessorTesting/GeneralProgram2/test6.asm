.data 

 .text

addi $1, $1, 5   #loop counter
addi $2, $2, 0
addi $3, $3, 2
ori $5, $5, 11
sw $5, 0($3)


loop:
addi $1, $1, -1
sll $3, $3, 1
lw $6 , 0($3)
nor $6, $6, $6
blt $2, $1, loop




end_program:
jal exit 
exit:
addi $0 , $0 , 0
jr $7
