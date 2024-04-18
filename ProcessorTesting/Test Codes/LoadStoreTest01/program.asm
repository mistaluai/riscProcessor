.data
val1: .word 10
val2: .word -5
result: .space 2
.text
# Load val1 into register $1
LW $1, 0($0)

# Load val2 into register $2
LW $2, 1($0)

# Perform addition: $3 = $1 + $2
ADD $3, $1, $2

# Store the result of addition into 'result' memory location
SW $3, 3($0)

# Load the result from memory back into register $4 for verification
LW $4, 3($0)