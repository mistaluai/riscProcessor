.data
arraySize: .word 30 0
.text
main:
	jal initialize_array #function to initialize array
	jal sum_array #function for sum the array
	jal halt_sim #jump into the last instruction indefinetly

initialize_array:
	add $1, $0, $0 #initialize counter
	lw $2, 0($0) #load array size
	init_loop:
		sw $1, 1($1) #store array element
		addi $1, $1, 1 #increment counter
		blt $1, $2, init_loop #loop if not reached array size
	jr $7
sum_array:
	add $1, $0, $0 #initialize counter
	lw $2, 0($0) #load array size
	add $4, $0, $0 #initialize output register
	sum_loop:
		lw $3, 1($1) #load array element
		add $4, $4, $3 #addition of array elements
		addi $1, $1, 1 #increment counter
		blt $1, $2, sum_loop #loop if not reached array size
	jr $7
halt_sim:
	j halt_sim
		
