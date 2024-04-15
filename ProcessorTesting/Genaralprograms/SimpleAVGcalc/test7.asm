.data 
.text

addi $1, $1, 8
addi $2, $2, 4

avg_calc:
jal sum_calc 
srl $4, $3, 1
j exit


sum_calc:
add $3, $1, $2
jr $7


exit:
addi $0 , $0 , 0
j exit
