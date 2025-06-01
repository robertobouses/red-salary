HEXAGONAL ARQUITECTURE

src/main/java/com/tuempresa/redsalary/
│
├── application/            ← Lógica de negocio (casos de uso)
│   └── service/
│       └── SalaryService.java
│
├── domain/                 ← Entidades y modelos puros (sin framework)
│   ├── model/
│   │   └── Salary.java
│   └── repository/
│       └── SalaryRepository.java  ← Interfaz (puerto)
│
├── infrastructure/         ← Adaptadores externos (DB, API, etc.)
│   ├── persistence/
│   │   ├── SalaryEntity.java
│   │   ├── JpaSalaryRepository.java  ← Adaptador
│   │   └── SalaryRepositoryImpl.java ← Implementación del puerto
│   └── config/
│       └── SpringConfig.java
│
├── api/                    ← Controladores REST (Adaptador de entrada)
│   └── SalaryController.java
│
└── RedSalaryApplication.java  ← Punto de entrada
│
└── docs
