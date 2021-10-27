function encrypt(s) {
    const {SHA3} = require('sha3')
    const hash = new SHA3(256)
    hash.update(s)
    return hash.digest('hex')
}

let fs = require('fs')
let files = fs.readdirSync('files')
let hashes = []
for (let f of files)
    hashes.push(encrypt(fs.readFileSync('files\\' + f)))
hashes = hashes.sort()
hashes.push('ivan.karpovich@gmail.com')
result = encrypt(hashes.join``)
console.log(result)
