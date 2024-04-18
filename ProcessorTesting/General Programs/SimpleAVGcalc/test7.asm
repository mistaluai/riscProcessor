.data 
.text
#initialize registers
addi $1, $1, 8
addi $2, $2, 4
addi $3, $3, 12
addi $4, $4, 9

avg_calc:
jal sum_calc #sum all numbers
srl $4, $4, 2 #divide their size
j exit


#addition of numbers
sum_calc:
add $2, $1, $2
add $3, $3, $2
add $4, $4, $3
jr $7


exit:
addi $0 , $0 , 0 #halt simulation
j exit
