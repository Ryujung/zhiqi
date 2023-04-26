import JSEnctypt from "jsencrypt/bin/jsencrypt.min"

// 密钥对生成 http://web.chacuo.net/netrsakeypair

const publicKey = `MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzw5+uF9GLSxV8mDGGTle
mfGDeF0o7+Tdz2/rAwaIP2DWl4TmaZbQBax3GwU1G4jjAV+zaF4tYoV9bNi4AwFN
NFTzOabqdq7MdRU+UNijJufvj4ogZupbOEUkLw04uHrE0GPTbznTsutGNzUKjcf2
MlR6Q227NBu17GBo2O0CPA3D3uwIp554UEAZHwqgu/+EfOfXiU0QL/EBDOcSR6kQ
9LApopFWW/g+cK4Iw7y5kHTbCId8sl3jlBPXHlRjeTjXqvgDUae+OrtD3HnmaIFB
QnT49Gu9YsPk5Mm+C5SmhB5YqukZvE6+B/YZNP2PjULNay6wURk0jbbUgWYF4Olt
2wIDAQAB`

const privateKey = `
MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDPDn64X0YtLFXy
YMYZOV6Z8YN4XSjv5N3Pb+sDBog/YNaXhOZpltAFrHcbBTUbiOMBX7NoXi1ihX1s
2LgDAU00VPM5pup2rsx1FT5Q2KMm5++PiiBm6ls4RSQvDTi4esTQY9NvOdOy60Y3
NQqNx/YyVHpDbbs0G7XsYGjY7QI8DcPe7AinnnhQQBkfCqC7/4R859eJTRAv8QEM
5xJHqRD0sCmikVZb+D5wrgjDvLmQdNsIh3yyXeOUE9ceVGN5ONeq+ANRp746u0Pc
eeZogUFCdPj0a71iw+Tkyb4LlKaEHliq6Rm8Tr4H9hk0/Y+NQs1rLrBRGTSNttSB
ZgXg6W3bAgMBAAECggEAHWxg45BeZ5LyT7Qi0njNkbC5TwRmCdkLECxGSDLIb8Ey
HWv8VirhOr0sW8Gcr2H/aDWSSBWB+4serLYLSBoKP5KG0b1/eggS89xl85UlciTp
bQti2V0aQ1/H6YvGcKi5iSd2Z0rfY4B9hODbtBQjTzmj+isAvEHn00RMAe+ELsZA
jEOLanitZkoyBrdu+mDu5Vta5uD0UsEXpERO5OhRe1hovmxubTaSjTlSrZU+ftu2
vXEmw6xV1bEAPQ1h0Eu+YdMZnlp303B5fpZIzTzFX8nA7S5p/UO6MPU8a8YImjbr
HEha9J1RtDNJ9JzZPpfYJQeOqlQr9sz9ochVkBxn+QKBgQDopv+tdhBdI5tIHd15
xfhThg5d7HzltRNc/z6K8fkkw7PdTT4GAT2hfXTMXpbMSrcul17+YKkdnhEXaoIt
znrO3uPcmaHAf6NiddHYxSFCIBwkPmiFEKo9N0Dz5LhJzQwfK5eMrzzUn+dY6B+s
kXbiyeZqCs5lK7FSDaRKLdJEHwKBgQDj1eyj0VDBZeLLUSCuSrjzmh/BhBPWQ2j/
FdLiScBZmsqmzWruOvrVJGBazMgiUoRtGrXAPcY26QpriAPUegR3+O03JPw6UWRT
44PeJroBJydqHM9fQNFZCsNwigbSTc225OcxDErxCLNK9GoN4YPgYVo0kzylPmza
skQ2HvS+xQKBgEAEG5wuPWhzcLnSRevI9oB9BZ0AgoluXsqbDSmMVWKxWt+M7oEW
fE/gRnApotLJyEfQrPnPvRkCMm/ABdv20cH0gLxlpaVjOaDRDJHaBEG+th+e8IGU
yVMYzg2OJMth2XEGPKggW9uhuDW1gWmpiQ30Qitxl8Ms15jdWhRzMUU/AoGALfZJ
D985IFrSftu18RNH7nTINTzU16Ovuc4IRfhE4o60lSPZj4e8dGUrPwYS1cG5z1zV
D5wmsYsfvAOraiDXZ/CCwl4muAkVaUr7nkRHv9XLQL/hIcEsun7OYJSnvaNOfbf9
38owECIGMXfPMU+uUASzsumFEXzfbtsJobVrAFUCgYAlcG6rPJkmU0j9dkWzixq5
XLvKAOwXudiJCfAXJ4fPBlp0He5+6N7XX9OHq1jrlCu/hcZ0E0AmZrz6/qkoWTc0
qASDX5xn2JICFqdYRkkGeFFRcHXKTrutXoY1bsW5/jOl9Q1kv6akEeASc4riwSnp
I4YJ6/WkCwlkfXXHZQJw3w==`

// 加密
export function encrypt(txt) {
  const encryptor = new JSEnctypt()
  encryptor.setPublicKey(publicKey) // 设置公钥来加密
  return  encryptor.encrypt(txt)
}

// 解密
export function decrypt(txt) {
  const encryptor = new JSEnctypt()
  encryptor.setPrivateKey(privateKey) // 设置私钥来解密
  return  encryptor.decrypt(txt)
}
