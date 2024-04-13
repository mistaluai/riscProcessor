.data
.text 
ori $1, $1, 1
bge $1, $0, L1                    
bge $2, $0, next                  
L1:
xor $3, $1, $2        


jal next
next:
addi $0 , $0 , 0
jr $7