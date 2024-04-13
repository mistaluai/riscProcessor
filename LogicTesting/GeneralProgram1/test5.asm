.data 
label: .word 1344
 .text

 lui 12
ori $1, $1, 5
addi $2, $2, 4
addi $3, $3, 2


sub $5, $1, $2
add $5, $5, $3 


slt $4, $5, $2
bge $4, $0, first_condition
bne $5, $0, second_condition

first_condition:
srl $6, $5, 2 

second_condition:
  xori $6, $5, 6
sw $6, 0($1)   


jal exit 
exit:
addi $0 , $0 , 0
jr $7
