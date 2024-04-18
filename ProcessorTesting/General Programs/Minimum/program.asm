.data 
.text
#initialize registers
addi $1, $1, 8
addi $2, $2, 14
addi $3, $3, 1
addi $6, $6, 0


blt $3, $2, minimum1 #compare third & second
blt $2,$1, minimum2 #compare second & first


minimum1:
blt $3, $1, minimum3 #compare third & first
add $6, $6, $1 #if we reached this then 3 is less than 2 & 1 is less than 3, then 1 is the minimum
j exit


minimum2:
add $6, $6, $2  #if we reached this then 2 is less than 3 & 2 is less than 1, then 2 is the minimum
j exit

minimum3:
add $6, $6, $3  #if we reached this then 3 is less than 2 & 3 is less than 1, then 3 is the minimum
j exit



exit:
addi $0 , $0 , 0 #halting simulation
j exit
