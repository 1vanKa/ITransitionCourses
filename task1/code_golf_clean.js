function clean(s) {
    s = s.split(' ');
    s = s.join('');
    s = s.split('\n');
    s = s.join('');
    console.log(s);
}

clean('[, , ...x] = process.argv;\n' +
    'if (x.length != 0)\n' +
    '    for (j = x[0].length; j > 0; j--)\n' +
    '        for (l = 0; l <= x[0].length - j;) {\n' +
    '            a = x[0].slice(l, l++ + j);\n' +
    '            for (i of x) if (i.search(a) == -1) a = null;\n' +
    '            if (a) {\n' +
    '                console.log(a);\n' +
    '                process.exit()\n' +
    '            }\n' +
    '        }')
