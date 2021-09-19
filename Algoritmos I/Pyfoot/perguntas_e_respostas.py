pergunta1 = """
s = 'banana'
s = s.split('a')
s1 = ''
for i in s:
    s1 += i
print(s1)"""

resposta1 = 'bnn'

pergunta2 = """
v = [10, 3, 5, 4, 8]
for i in range(len(v)):
    for j in range(i+1, len(v)):
        if v[j] < v[i]:
            v[i], v[j] = v[j], v[i]
print(v[2])"""

resposta2 = '5'

pergunta3 = """
num = 177
print(num % 2 == 0)"""

resposta3 = 'False'

pergunta4 = """
v = [1, 2, 3, 4, 5]
soma = 0
for n in v:
    if n % 2 == 0:
        soma += n
print(soma)"""

resposta4 = '6'

pergunta5 = """
v = [3, 2, 1, 0]
v2 = v[1:]
soma = 0
for i in v2:
    soma += i
print(soma)"""

resposta5 = '3'

pergunta6 = """
s = 'UFMA'
print(s[-1])"""

resposta6 = 'A'

pergunta7 = """
s = 'UFMA'
print(s[2:])"""

resposta7 = 'MA'

pergunta8 = """
def func(num):
    if num % 2 == 0 and num >= 0:
        return True
    return False

if func(-8):
    print('Sim')
else:
    print('Não')"""

resposta8 = 'Não'

pergunta9 = """
def func(v):
    return len(v) % 2 != 0

v = ['A', 'B', 'C', 'D', 'E']    
print(func(v))"""

resposta9 = 'True'

pergunta10 = """
v = []
i = 1
while i <= 10:
    if i % 2 == 0:
        v.append(i)
    i += 1
print(v[3])"""

resposta10 = '8'

pergunta11 = """
v = []
for i in range(10):
    for j in range(10):
        v.append(i+j)
if 19 in v:
    print('Sim')
else:
    print('Não')"""

resposta11 = 'Não'

pergunta12 = """
s = 'UFMA'
for i in range(1, 11):
    if i % 3 == 0:
        s += str(i)
    elif i % 5 == 0:
        s += str(i+1)
print(s)"""

resposta12 = 'UFMA366911'

pergunta13 = """
v = [1, 3, 5, 7, 9]
v2 = []
for i in range(1, len(v)+1):
    v2.append(v[-i])
print(v2[3])"""

resposta13 = '3'

pergunta14 = """
s = 'UFMA'
s2 = ''
for i in range(1, len(s)+1):
    s2 += s[-i]
print(s2)"""

resposta14 = 'AMFU'

pergunta15 = """
n = '1'
n += '0'
n = int(n)
n = n//3
print(n)"""

resposta15 = '3'

pergunta16 = """
a = 3
b = '5'
c = str(a) + b
a += int(c)
print(a)"""

resposta16 = '38'

pergunta17 = """
def func(a, b, c):  
    return b**2 - 4*a*c
    
print(func(1, -6, 5))"""

resposta17 = '16'

pergunta18 = """
s = '11'
print(s*2)"""

resposta18 = '1111'

pergunta19 = """
a = '10'
b = 10
print(a==b)"""

resposta19 = 'False'

pergunta20 = """
a = 10.0
b = 10
print(a==b)"""

resposta20 = 'True'

pergunta21 = """
v = [1, 2, 3, 4]
v.append(v[3] + 1)
print(v[-1])"""

resposta21 = '5'

pergunta22 = """
v = [1, 2, 3, 4]
print(v[-1] == v[3])"""

resposta22 = 'True'

pergunta23 = """
s = ''
for i in range(1, 10, 2):
    s += str(i)
n = int(s) // 100
print(n)"""

resposta23 = '135'

pergunta24 = """
v = []
for i in range(2, 20, 3):
    v.append(i)
print(14 in v)"""

resposta24 = 'True'

pergunta25 = """
v = []
for i in range(2, 20, 3):
    v.append(i)
print(20 in v)"""

resposta25 = 'False'

pergunta26 = """
def func(num):
    return num ** 2

print(func(12))"""

resposta26 = '144'

pergunta27 = """
def func(num1, num2):
    return num1 ** num2

print(func(2, 5))"""

resposta27 = '32'

pergunta28 = """
def func(n1, n2):
    soma = 0
    for i in range(0, n1+1, n2):
        soma += i
    return soma

print(func(10, 2))"""

resposta28 = '30'

perguntas = []
respostas = []

for i in range(1, 29):
    perguntas.append(eval(f'pergunta{i}'))
    respostas.append(eval(f'resposta{i}'))