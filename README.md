# FrontEnd

## Preparar ambiente com Virtual Machine (recomendado)

1. abrir o Virtual Box e importar o arquivo Hackathon - ALTRAN.ova
1. Dentro da máquina virtual, abrir o emulador e em seguida o VSCode
1. Abrir o Hello World no emulador
1. Abrir o comemu e apontar para o diretório C:\Users\HACKATHON\Documents\gmapp
1. executar o comando `$ ng build --watch` no cmd. Se der erro, será necessário apagar o diretório dist/ e executar o comando novamente.
1. Para que o watch funcione, execute os seguintes passos:
    - No Emulador: Carregue em Application > Close App
    - Carregar com o direito do rato sobre o app e carregue em "Compile and Open App". 
    - Alterar algo no código e salvar. Esperar o build e veja se foi atualizado no emulador (não se preocupe com fontes alteradas, pois isso acontece somente no watch).



## Preparar ambiente com Virtual Machine (NÃO recomendado)

### Requisitos

- NodeJS
  - Versão 10.15.3 (podes usar nvm)

- Package GM
  - deve ser feita a partir do CMD do windows **em modo admin**, pois sua instalação usando outro terminal como o Git Bash apresentou problemas.
  - Excluir todos os pacotes globais instalados previamente devido a conflitos no momento de iniciar um novo projeto.
  - Antes de rodar o comando, dar permissão de escrita no diretório *package* inteiro, incluindo todos os ficheiros internos.



### NGI-Emulator-1.9.0
  1. Instalar normalmente em seu sistema operacional
  1. abrir o emulador
  1. carregar em File > Create App. Faça as seguintes seleções:
      - **Choose an app template:** Blank Angular (2+) App
      - **What type of network connectivity is required?** Constant (audio streaming or location tracking)
      - **What category of app is this?** General
  1. Carregar em **Create App**
  1. Escolha um diretório. Não se preocupe com a mensagem de erro. Carregue em **Cancel**
  1. Abra o diretório no cmd com permissão de admin. Digite os comandos `npm install`.
  1. Instalar o angular-cli globalmente e executar o comando `ng build`
  1. Abra o emulador. Carregue em File > Open App. Aponte para o diretório criado e carregue em **Select Forlder**.
  1. Para habilitar o watch, execute os seguintes passos:
      - No Emulador: Carregue em Application > Close App
      - executar o comando `$ ng build --watch` no cmd aberto como administrator
      - Carregar com o direito do rato sobre o app e carregue em "Compile and Open App". 
      - Alterar algo no código e salvar. Esperar o build e veja se foi atualizado no emulador (não se preocupe com fontes alteradas, pois isso acontece somente no watch).

## Obter a localização

Para obter a localização do veículo na aplicação, use o objeto global disponibilizado pelo framework.

```js
gm.vehicle.getCurrentPosition( data => {
      this.info = `
        latitude: ${data.coords.latitude} -
        longitude: ${data.coords.longitude} -
        heading: ${data.coords.heading} -
        altitude: ${data.coords.altitude}`;
    } )
```
Talvez tu tenhas problemas com o typescript por não ter um type definition para o objeto gm. Como solucionar o problema faz parte do desafio.

# Backend

## Baseline

Será provida uma baseline para o backend. Esta consiste numa aplicação utilizando o Spring Boot Framework, acessando uma base de dados H2 em memória, já com a infraestrutura de acesso a dados e segurança configurada. A baseline propõe uma arquitetura em camadas, a saber: controllers/services/repositories/model. Tanto a arquitetura da solução quanto os frameworks adotados fazem parte de uma proposta e os participantes estão livres para adotar quaisquer outros estilos arquiteturais. No entanto, caso adotem a solução proposta, terão consultores disponíveis para prestar esclarecimentos sobre como a baseline foi construída.

## Requisitos

Além da implementação das features, faz parte dos requisitos deste hackaton a conversão deste módulo para um banco de dados persistente, como MySQL/Postgree SQL. O acesso aos dados é feito utilizando o framework Spring Data JPA. A segurança está configurada através do framework Spring Security. Tanto a configuração de acesso a dados quanto a configuração da segurança da aplicação podem demandar alterações com a construção das features restantes. Tais alterações também fazem parte do desafio.

Good Hacking!

# MobileApp

## Notas de instalação

Download link:
https://developer.android.com/studio

Usual instalation, this will install all the required dependencies out of the box.
After instalation please create a virtual device, using AVD Manager, of Tablet->10.1'' WXGA 800x1280 mdpi->Android Oreo 8.0->Landscape
