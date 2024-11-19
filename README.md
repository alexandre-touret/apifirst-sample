# Lint

```jshelllanguage
vacuum lint -d src/main/resources/apifirst_sample-openapi.yaml
```

# Changelog

```jshelllanguage
 oasdiff changelog src/main/resources/apifirst_sample-openapi-OK.yaml src/main/resources/apifirst_sample-openapi-OK-v2.
yaml

```


# Create mocks
```bash
docker run -p 8585:8080 -it --rm quay.io/microcks/microcks-uber:latest-native
```
