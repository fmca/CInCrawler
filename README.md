# CInCrawler

Crawler para extrair informações disponíveis no website do CIn / UFPE.

Atualmente descreve informações dos docentes em três formatos: xml, json ou excel.

```gradle run -Dargs="--help"```

```
Usage: <main class> [options]
    -c, -crawlers
       Número de crawlers concorrentes
       Default: 7
    -e, -entidade
       Qual entidade o crawler deve gerar. (apenas docentes implementado)
       Default: docente
       Possible Values: [docente]
  * -f, -filtrar
       String que a url deve conter para ser processada
    -s, -saida
       Tipo de saída.
       Default: xml
       Possible Values: [xml, json, excel]
    -u, -url
       Url a ser visitada
       Default: http://www2.cin.ufpe.br/
  * -v, -visitar
       String que a url deve conter para ser visitada
       
```

###Exemplo de execução

```gradle run -Dargs="-url http://www2.cin.ufpe.br/site/listaContatos.php?s=1&c=8 -visitar listaContatos -filtrar lerContato -saida excel"```
