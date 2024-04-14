.data

.text

addi $1 , $0 , 1
addi $2 , $0 , 5
addi $3, $0 , 7
sll $4 , $3 , 1
addi $5, $0 , 2
sw $4 , 0($5)
addi $3 , $3 , 1
srl $3 , $3 , 1
lw $6 , 0($5)
and $5 , $3 , $5
bne $5 , $0 , else
addi $6 , $6 , 1
j label
else:
addi $6 , $0 , 0
label:
jal exit
exit:
jr $7
 
